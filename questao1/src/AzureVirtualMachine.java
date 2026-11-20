public class AzureVirtualMachine implements VirtualMachine {
    private final String id;

    public AzureVirtualMachine(int sequence) {
        this.id = "vm-" + sequence;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getHourlyCost() {
        return 0.12;
    }
}
