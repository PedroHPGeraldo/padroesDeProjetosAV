public class Main {
    public static void main(String[] args) {
        Participant alice = new Participant("Alice");
        Participant bruno = new Participant("Bruno");
        Participant carla = new Participant("Carla");

        AuctioneerPanel panel = new AuctioneerPanel();
        BidAuditor auditor = new BidAuditor();

        AuctionLot artLot = new AuctionLot(
                "Quadro Modernista",
                "Arte",
                1000.00,
                1200.00,
                new ArtCommissionStrategy()
        );

        AuctionLot vehicleLot = new AuctionLot(
                "Sedan Clássico 1995",
                "Veículos",
                20000.00,
                25000.00,
                new VehicleCommissionStrategy()
        );

        artLot.addObserver(alice);
        artLot.addObserver(bruno);
        artLot.addObserver(carla);
        artLot.addObserver(panel);
        artLot.addObserver(auditor);

        vehicleLot.addObserver(alice);
        vehicleLot.addObserver(bruno);
        vehicleLot.addObserver(panel);
        vehicleLot.addObserver(auditor);

        artLot.placeBid(alice, 1020.00);
        artLot.placeBid(alice, 1050.00);

        artLot.removeObserver(carla);

        artLot.placeBid(bruno, 1150.00);
        artLot.placeBid(alice, 1300.00);

        artLot.setCommissionStrategy(new ElectronicsCommissionStrategy());

        artLot.closeLot();
        artLot.placeBid(bruno, 1500.00);

        vehicleLot.placeBid(bruno, 21000.00);
        vehicleLot.closeLot();

        InvoiceItem invoice = new BaseLotInvoice(artLot);
        invoice = new TransportInsuranceDecorator(invoice);
        invoice = new AuthenticityCertificateDecorator(invoice);
        invoice = new SpecialPackagingDecorator(invoice);
        invoice = new SpecialPackagingDecorator(invoice);

        InvoicePrinter.print(invoice);
        artLot.printSellerSettlement();
    }
}
