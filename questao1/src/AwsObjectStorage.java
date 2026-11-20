public class AwsObjectStorage implements ObjectStorage {
    private final String id;

    public AwsObjectStorage(int sequence) {
        this.id = "s3://" + sequence;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyCostPerGb() {
        return 0.023;
    }
}
