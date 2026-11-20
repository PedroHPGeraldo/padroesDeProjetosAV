import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static volatile ConfigManager instance;
    private final String defaultRegion;
    private final double maxMonthlyBudget;

    private ConfigManager() {
        Properties props = new Properties();
        try (InputStream input = openConfigStream()) {
            props.load(input);
            this.defaultRegion = props.getProperty("regiao", "us-east-1");
            this.maxMonthlyBudget = Double.parseDouble(props.getProperty("orcamento", "200.00"));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
    }

    private static InputStream openConfigStream() throws IOException {
        String[] paths = {"config.properties", "questao1/config.properties", "../config.properties"};
        for (String path : paths) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                return new FileInputStream(file);
            }
        }
        InputStream resource = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");
        if (resource != null) {
            return resource;
        }
        throw new IOException("Arquivo config.properties não encontrado.");
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public String getDefaultRegion() {
        return defaultRegion;
    }

    public double getMaxMonthlyBudget() {
        return maxMonthlyBudget;
    }
}
