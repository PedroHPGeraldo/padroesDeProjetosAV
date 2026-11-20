public class Main {
    public static void main(String[] args) throws InterruptedException {
        EnvironmentProvisioner provisioner = new EnvironmentProvisioner();

        CloudResourceFactory awsFactory = new AwsResourceFactory();
        CloudResourceFactory azureFactory = new AzureResourceFactory();

        EnvironmentRequest req1 = new EnvironmentRequest("Prod-API-AWS", awsFactory, 2, 500);
        EnvironmentRequest req2 = new EnvironmentRequest("Staging-AWS", awsFactory, 1, 100);
        EnvironmentRequest req3 = new EnvironmentRequest("Analytics-Azure", azureFactory, 2, 300);
        EnvironmentRequest req4 = new EnvironmentRequest("BigData-OverBudget", azureFactory, 10, 5000);

        Thread t1 = new Thread(() -> provisioner.provision(req1));
        Thread t2 = new Thread(() -> provisioner.provision(req2));
        Thread t3 = new Thread(() -> provisioner.provision(req3));
        Thread t4 = new Thread(() -> provisioner.provision(req4));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        AuditLogger.getInstance().printFullHistory();
    }
}
