import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BidAuditor implements LotObserver {
    private final List<String> auditLog = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onBidPlaced(AuctionLot lot, Bid bid) {
        String entry = String.format("[%s] Lote: %s | Participante: %s | Valor: R$ %.2f",
                bid.getTimestamp().format(FORMATTER), lot.getName(), bid.getBidder().getName(), bid.getAmount());
        auditLog.add(entry);
        System.out.println("[Auditor] Registrado -> " + entry);
    }

    public List<String> getAuditLog() {
        return new ArrayList<>(auditLog);
    }
}
