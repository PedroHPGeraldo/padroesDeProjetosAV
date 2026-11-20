public class AzureObjectStorage implements ObjectStorage {
    private final String id;

    public AzureObjectStorage(int sequence) {
        this.id = "blob://" + sequence;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getMonthlyCostPerGb() {
        return 0.02;
    }
}
