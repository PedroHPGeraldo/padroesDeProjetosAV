public class AwsVirtualMachine implements VirtualMachine {
    private final String id;

    public AwsVirtualMachine(int sequence) {
        this.id = "i-" + sequence;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getHourlyCost() {
        return 0.10;
    }
}
