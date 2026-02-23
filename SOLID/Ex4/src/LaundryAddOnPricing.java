public class LaundryAddOnPricing implements AddOnPrice {
    public AddOn supports() { return AddOn.LAUNDRY; }
    public Money monthlyFee() { return new Money(500.0); }
}