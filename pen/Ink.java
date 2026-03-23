/**
 * Interface for Ink implementations
 * Abstraction to support different ink types (Ballpoint, Gel, Fountain, etc.)
 * Follows Interface Segregation Principle - clients only depend on methods they use
 * Follows Dependency Inversion Principle - depend on abstraction, not concrete implementation
 */
public interface Ink {
    /**
     * Consume ink based on writing
     */
    void consume(int wordCount);
    
    /**
     * Refill the ink
     */
    void refill();
    
    /**
     * Get current ink quantity
     */
    double getQuantity();
    
    /**
     * Check if ink is empty
     */
    boolean isEmpty();
    
    /**
     * Get ink color
     */
    String getColor();
    
    /**
     * Set ink color
     */
    void setColor(String color);
    
    /**
     * Get ink type
     */
    String getType();
}
