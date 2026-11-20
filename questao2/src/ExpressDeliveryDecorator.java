public class ExpressDeliveryDecorator extends AdditionalServiceDecorator {
    private static final double FIXED_COST = 450.00;

    public ExpressDeliveryDecorator(InvoiceItem wrappedInvoice) {
        super(wrappedInvoice);
    }

    @Override
    public double getTotalCost() {
        return wrappedInvoice.getTotalCost() + FIXED_COST;
    }

    @Override
    public String getDetails() {
        return wrappedInvoice.getDetails()
                + String.format("%n  + Entrega Expressa: R$ %.2f", FIXED_COST);
    }
}
