public class TransportInsuranceDecorator extends AdditionalServiceDecorator {
    public TransportInsuranceDecorator(InvoiceItem wrappedInvoice) {
        super(wrappedInvoice);
    }

    private double getServiceCost() {
        return getSalePrice() * 0.015;
    }

    @Override
    public double getTotalCost() {
        return wrappedInvoice.getTotalCost() + getServiceCost();
    }

    @Override
    public String getDetails() {
        return wrappedInvoice.getDetails()
                + String.format("%n  + Seguro de Transporte (1,5%%): R$ %.2f", getServiceCost());
    }
}
