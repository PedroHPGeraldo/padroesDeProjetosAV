import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnvironmentProvisioner {
    private static final int HOURS_PER_MONTH = 730;

    public void provision(EnvironmentRequest request) {
        CloudResourceFactory factory = request.getFactory();
        ConfigManager config = ConfigManager.getInstance();
        AuditLogger audit = AuditLogger.getInstance();

        double estimatedCost = (request.getVmCount() * factory.getVmHourlyCost() * HOURS_PER_MONTH)
                + (request.getStorageGb() * factory.getStorageMonthlyCostPerGb())
                + factory.getQueueMonthlyFixedCost();

        if (estimatedCost > config.getMaxMonthlyBudget()) {
            String reason = String.format("Custo estimado R$ %.2f excede o orçamento máximo R$ %.2f",
                    estimatedCost, config.getMaxMonthlyBudget());
            audit.log(request.getEnvironmentName(), factory.getProviderName(), false, reason);
            return;
        }

        List<VirtualMachine> vms = new ArrayList<>();
        for (int i = 0; i < request.getVmCount(); i++) {
            vms.add(factory.createVirtualMachine());
        }
        ObjectStorage storage = factory.createObjectStorage();
        MessageQueue queue = factory.createMessageQueue();

        String vmIds = vms.stream().map(VirtualMachine::getId).collect(Collectors.joining(", "));

        audit.log(request.getEnvironmentName(), factory.getProviderName(), true, "Provisionado com sucesso");

        synchronized (System.out) {
            System.out.println("--------------------------------------------------");
            System.out.println("Ambiente: " + request.getEnvironmentName());
            System.out.println("Provedor: " + factory.getProviderName());
            System.out.println("Região: " + config.getDefaultRegion());
            System.out.println("VMs: [" + vmIds + "]");
            System.out.println("Armazenamento: " + storage.getId() + " (" + request.getStorageGb() + " GB)");
            System.out.println("Fila: " + queue.getId());
            System.out.printf("Custo mensal estimado: R$ %.2f%n", estimatedCost);
        }
    }
}
