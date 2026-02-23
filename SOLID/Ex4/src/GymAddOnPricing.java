public class GymAddOnPricing implements AddOnPrice {
    public AddOn supports() { return AddOn.GYM; }
    public Money monthlyFee() { return new Money(300.0); }
}