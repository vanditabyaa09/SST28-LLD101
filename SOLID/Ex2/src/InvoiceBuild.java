import java.util.*;

public class InvoiceBuild {

    public String build(
            String invId,
            List<String> itemLines,
            double subtotal,
            double taxPct,
            double tax,
            double discount,
            double total
    ) {
        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invId).append("\n");

        for (String l : itemLines) {
            out.append(l);
        }

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        return InvoiceFormatter.identityFormat(out.toString());
    }
}