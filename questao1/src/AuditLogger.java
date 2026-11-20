import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuditLogger {
    private static volatile AuditLogger instance;
    private final List<String> records;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private AuditLogger() {
        this.records = Collections.synchronizedList(new ArrayList<>());
    }

    public static AuditLogger getInstance() {
        if (instance == null) {
            synchronized (AuditLogger.class) {
                if (instance == null) {
                    instance = new AuditLogger();
                }
            }
        }
        return instance;
    }

    public synchronized void log(String environmentName, String provider, boolean accepted, String reason) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String status = accepted ? "ACEITO" : "RECUSADO";
        String entry = String.format("[%s] Ambiente: %s | Provedor: %s | Resultado: %s (%s)",
                timestamp, environmentName, provider, status, reason);
        records.add(entry);
    }

    public synchronized void printFullHistory() {
        System.out.println("\n=== HISTÓRICO DE AUDITORIA ===");
        for (String record : records) {
            System.out.println(record);
        }
    }
}
