import java.util.List;

public class DefaultDiscountPol implements DiscountPolicy {
    @Override
    public double discountAmount(String customerType, double subtotal, List<OrderLine> lines) {
        return DiscountRules.discountAmount(customerType, subtotal, lines.size());
    }
}