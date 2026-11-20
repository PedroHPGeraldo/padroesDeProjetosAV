public abstract class AdditionalServiceDecorator implements InvoiceItem {
    protected final InvoiceItem wrappedInvoice;

    protected AdditionalServiceDecorator(InvoiceItem wrappedInvoice) {
        this.wrappedInvoice = wrappedInvoice;
    }

    @Override
    public String getLotName() {
        return wrappedInvoice.getLotName();
    }

    @Override
    public double getSalePrice() {
        return wrappedInvoice.getSalePrice();
    }
}
