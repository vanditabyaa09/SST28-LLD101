public class MessAddOnPricing implements AddOnPrice {
    public AddOn supports() { return AddOn.MESS; }
    public Money monthlyFee() { return new Money(1000.0); }
}