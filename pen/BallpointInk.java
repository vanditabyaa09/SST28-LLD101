/**
 * Ballpoint Ink Implementation
 * Follows Single Responsibility Principle - only manages ballpoint ink behavior
 */
public class BallpointInk implements Ink {
    private double quantity;
    private String color;
    private final double maxCapacity;
    private static final double CONSUMPTION_RATE = 0.1; // ml per word
    
    public BallpointInk(double maxCapacity, String color) {
        this.maxCapacity = maxCapacity;
        this.quantity = maxCapacity;
        this.color = color;
    }
    
    @Override
    public void consume(int wordCount) {
        double consumed = wordCount * CONSUMPTION_RATE;
        if (consumed <= quantity) {
            quantity -= consumed;
        } else {
            quantity = 0;
        }
    }
    
    @Override
    public void refill() {
        this.quantity = maxCapacity;
    }
    
    @Override
    public double getQuantity() {
        return quantity;
    }
    
    @Override
    public boolean isEmpty() {
        return quantity <= 0;
    }
    
    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getType() {
        return "Ballpoint";
    }
}
