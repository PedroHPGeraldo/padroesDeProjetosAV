import java.util.concurrent.atomic.AtomicInteger;

public class AwsResourceFactory implements CloudResourceFactory {
    private static final AtomicInteger vmSeq = new AtomicInteger(1);
    private static final AtomicInteger storageSeq = new AtomicInteger(1);
    private static final AtomicInteger queueSeq = new AtomicInteger(1);

    @Override
    public String getProviderName() {
        return "Aws";
    }

    @Override
    public double getVmHourlyCost() {
        return 0.10;
    }

    @Override
    public double getStorageMonthlyCostPerGb() {
        return 0.023;
    }

    @Override
    public double getQueueMonthlyFixedCost() {
        return 0.40;
    }

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AwsVirtualMachine(vmSeq.getAndIncrement());
    }

    @Override
    public ObjectStorage createObjectStorage() {
        return new AwsObjectStorage(storageSeq.getAndIncrement());
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AwsMessageQueue(queueSeq.getAndIncrement());
    }
}
