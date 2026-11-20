import java.util.concurrent.atomic.AtomicInteger;

public class AzureResourceFactory implements CloudResourceFactory {
    private static final AtomicInteger vmSeq = new AtomicInteger(1);
    private static final AtomicInteger storageSeq = new AtomicInteger(1);
    private static final AtomicInteger queueSeq = new AtomicInteger(1);

    @Override
    public String getProviderName() {
        return "Azure";
    }

    @Override
    public double getVmHourlyCost() {
        return 0.12;
    }

    @Override
    public double getStorageMonthlyCostPerGb() {
        return 0.02;
    }

    @Override
    public double getQueueMonthlyFixedCost() {
        return 0.50;
    }

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AzureVirtualMachine(vmSeq.getAndIncrement());
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AzureObjectStorage(storageSeq.getAndIncrement());
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AzureMessageQueue(queueSeq.getAndIncrement());
    }
}
