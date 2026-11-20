public class AzureMessageQueue implements MessageQueue {
    private final String id;

    public AzureMessageQueue(int sequence) {
        this.id = "sb-" + sequence;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyFixedCost() {
        return 0.50;
    }
}
