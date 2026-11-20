import java.util.HashMap;
import java.util.Map;

public class AuctioneerPanel implements LotObserver {
    private final Map<String, Double> highestBids = new HashMap<>();
    private final Map<String, Integer> totalBids = new HashMap<>();

    @Override
    public void onBidPlaced(AuctionLot lot, Bid bid) {
        String lotName = lot.getName();
        int count = totalBids.getOrDefault(lotName, 0) + 1;
        totalBids.put(lotName, count);

        double highest = Math.max(highestBids.getOrDefault(lotName, 0.0), bid.getAmount());
        highestBids.put(lotName, highest);

        System.out.printf("[Painel do Leiloeiro - %s] Maior lance: R$ %.2f | Total de lances: %d%n",
                lotName, highest, count);
    }
}
