public class SpecialPackagingDecorator extends AdditionalServiceDecorator {
    private static final double FIXED_COST = 180.00;

    public SpecialPackagingDecorator(InvoiceItem wrappedInvoice) {
        super(wrappedInvoice);
    }

    @Override
    public double getTotalCost() {
        return wrappedInvoice.getTotalCost() + FIXED_COST;
    }

    @Override
    public String getDetails() {
        return wrappedInvoice.getDetails()
                + String.format("%n  + Embalagem Especial: R$ %.2f", FIXED_COST);
    }
}
