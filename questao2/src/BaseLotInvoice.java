public class BaseLotInvoice implements InvoiceItem {
    private final String lotName;
    private final double salePrice;

    public BaseLotInvoice(AuctionLot lot) {
        if (!lot.isSold()) {
            throw new IllegalStateException("Não é possível gerar fatura para lote não vendido.");
        }
        this.lotName = lot.getName();
        this.salePrice = lot.getCurrentPrice();
    }

    @Override
    public String getLotName() {
        return lotName;
    }

    @Override
    public double getSalePrice() {
        return salePrice;
    }

    @Override
    public double getTotalCost() {
        return salePrice;
    }

    @Override
    public String getDetails() {
        return String.format("Lote: %s%nValor de venda: R$ %.2f", lotName, salePrice);
    }
}
