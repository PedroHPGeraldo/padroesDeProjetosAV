import java.util.ArrayList;
import java.util.List;

public class AuctionLot {
    private final String name;
    private final String category;
    private final double initialPrice;
    private final double reservePrice;
    private double currentPrice;
    private boolean open;
    private boolean sold;
    private Bid winningBid;
    private CommissionStrategy commissionStrategy;
    private final List<Bid> bidHistory = new ArrayList<>();
    private final List<LotObserver> observers = new ArrayList<>();

    public AuctionLot(String name, String category, double initialPrice, double reservePrice, CommissionStrategy commissionStrategy) {
        this.name = name;
        this.category = category;
        this.initialPrice = initialPrice;
        this.reservePrice = reservePrice;
        this.currentPrice = initialPrice;
        this.open = true;
        this.sold = false;
        this.commissionStrategy = commissionStrategy;
    }

    public void addObserver(LotObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(LotObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Bid bid) {
        for (LotObserver observer : new ArrayList<>(observers)) {
            observer.onBidPlaced(this, bid);
        }
    }

    public void setCommissionStrategy(CommissionStrategy commissionStrategy) {
        if (!open) {
            System.out.printf("Alteração de comissão recusada no lote '%s': o lote já está encerrado.%n", name);
            return;
        }
        this.commissionStrategy = commissionStrategy;
    }

    public boolean placeBid(Bidder bidder, double amount) {
        if (!open) {
            System.out.printf("Lance recusado no lote '%s': o lote já está encerrado.%n", name);
            return false;
        }
        double minimumRequired = currentPrice * 1.05;
        if (amount < minimumRequired) {
            System.out.printf("Lance de R$ %.2f de %s recusado no lote '%s': o valor deve ser pelo menos 5%% maior que R$ %.2f (mínimo R$ %.2f).%n",
                    amount, bidder.getName(), name, currentPrice, minimumRequired);
            return false;
        }

        Bid bid = new Bid(bidder, amount);
        this.currentPrice = amount;
        this.winningBid = bid;
        this.bidHistory.add(bid);
        System.out.printf("Lance aceito: %s ofertou R$ %.2f no lote '%s'.%n", bidder.getName(), amount, name);
        notifyObservers(bid);
        return true;
    }

    public void closeLot() {
        if (!open) return;
        this.open = false;
        if (winningBid != null && winningBid.getAmount() >= reservePrice) {
            this.sold = true;
            System.out.printf("Lote '%s' ENCERRADO e VENDIDO para %s por R$ %.2f.%n",
                    name, winningBid.getBidder().getName(), winningBid.getAmount());
        } else {
            this.sold = false;
            System.out.printf("Lote '%s' ENCERRADO como NÃO VENDIDO (preço de reserva de R$ %.2f não atingido).%n",
                    name, reservePrice);
        }
    }

    public void printSellerSettlement() {
        if (!sold || winningBid == null) {
            System.out.printf("Acerto indisponível: lote '%s' não foi vendido.%n", name);
            return;
        }
        double salePrice = winningBid.getAmount();
        double commission = commissionStrategy.calculate(salePrice);
        double netAmount = salePrice - commission;

        System.out.println("\n=== ACERTO DO VENDEDOR ===");
        System.out.println("Lote: " + name + " (" + category + ")");
        System.out.printf("Valor de venda: R$ %.2f%n", salePrice);
        System.out.printf("Comissão cobrada: R$ %.2f%n", commission);
        System.out.printf("Valor líquido a repassar: R$ %.2f%n", netAmount);
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public boolean isSold() {
        return sold;
    }
}
