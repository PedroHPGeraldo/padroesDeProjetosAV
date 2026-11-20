public class InvoicePrinter {
    public static void print(InvoiceItem invoice) {
        System.out.println("\n=== FATURA DO COMPRADOR ===");
        System.out.println(invoice.getDetails());
        System.out.printf("Total a pagar: R$ %.2f%n", invoice.getTotalCost());
    }
}
