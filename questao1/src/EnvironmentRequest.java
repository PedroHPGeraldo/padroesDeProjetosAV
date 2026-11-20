public class EnvironmentRequest {
    private final String environmentName;
    private final CloudResourceFactory factory;
    private final int vmCount;
    private final double storageGb;

    public EnvironmentRequest(String environmentName, CloudResourceFactory factory, int vmCount, double storageGb) {
        this.environmentName = environmentName;
        this.factory = factory;
        this.vmCount = vmCount;
        this.storageGb = storageGb;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public CloudResourceFactory getFactory() {
        return factory;
    }

    public int getVmCount() {
        return vmCount;
    }

    public double getStorageGb() {
        return storageGb;
    }
}
