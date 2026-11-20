public interface CloudResourceFactory {
    String getProviderName();
    double getVmHourlyCost();
    double getStorageMonthlyCostPerGb();
    double getQueueMonthlyFixedCost();
    VirtualMachine createVirtualMachine();
    ObjectStorage createObjectStorage();
    MessageQueue createMessageQueue();
}
