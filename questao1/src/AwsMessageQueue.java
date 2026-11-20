public class AwsMessageQueue implements MessageQueue {
    private final String id;

    public AwsMessageQueue(int sequence) {
        this.id = "sqs-" + sequence;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyFixedCost() {
        return 0.40;
    }
}
