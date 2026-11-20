public class Participant implements LotObserver, Bidder {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void onBidPlaced(AuctionLot lot, Bid bid) {
        if (!bid.getBidder().equals(this)) {
            System.out.printf("[Notificação -> %s] Novo lance no lote '%s': R$ %.2f%n",
                    name, lot.getName(), bid.getAmount());
        }
    }
}
