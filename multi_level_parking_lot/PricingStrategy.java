package multi_level_parking_lot;
public interface PricingStrategy {
    double calculateFee(Ticket ticket, long exitTimeMillis);
}