public class AuthenticityCertificateDecorator extends AdditionalServiceDecorator {
    private static final double FIXED_COST = 300.00;

    public AuthenticityCertificateDecorator(InvoiceItem wrappedInvoice) {
        super(wrappedInvoice);
    }

    @Override
    public double getTotalCost() {
        return wrappedInvoice.getTotalCost() + FIXED_COST;
    }

    @Override
    public String getDetails() {
        return wrappedInvoice.getDetails()
                + String.format("%n  + Certificado de Autenticidade: R$ %.2f", FIXED_COST);
    }
}
