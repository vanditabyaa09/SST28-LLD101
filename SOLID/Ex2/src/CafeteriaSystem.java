import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceBuild builder = new InvoiceBuild();

    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.store = new FileStore();
        this.taxPolicy = new DefaultTaxPolicy();
        this.discountPolicy = new DefaultDiscountPol();
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = 0.0;
        List<String> itemLines = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;

            itemLines.add(
                String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal)
            );
        }

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, lines);

        double total = subtotal + tax - discount;

        String printable = builder.build(
                invId, itemLines, subtotal, taxPct, tax, discount, total
        );

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId +
                " (lines=" + store.countLines(invId) + ")");
    }
}