/**
 * Represents a Pen with start, write, close, and refill functionalities
 * 
 * SOLID Principles Applied:
 * - Single Responsibility: Only orchestrates pen operations, delegates to specialized classes
 * - Open/Closed: Can extend with new ink types and writing strategies without modification
 * - Liskov Substitution: Can use any Ink implementation or WritingService implementation
 * - Interface Segregation: Depends only on necessary interfaces
 * - Dependency Inversion: Depends on abstractions (Ink, WritingService), not concrete classes
 */
public class Pen {
    private final String brand;
    private final Ink ink;
    private final PenStateManager stateManager;
    private final WritingService writingService;
    private final PenStatistics statistics;
    
    /**
     * Constructor with Dependency Injection
     * Allows flexibility in choosing different ink types and writing strategies
     */
    public Pen(String brand, Ink ink, WritingService writingService) {
        this.brand = brand;
        this.ink = ink;
        this.writingService = writingService;
        this.stateManager = new PenStateManager();
        this.statistics = new PenStatistics();
    }
    
    /**
     * Open the pen cap
     */
    public void start() {
        if (stateManager.open()) {
            System.out.println("Pen opened. Ready to write!");
        } else if (!stateManager.isClosed()) {
            System.out.println("Pen is already open.");
        }
    }
    
    /**
     * Write text using the injected writing service
     * Delegates writing logic to WritingService (Strategy Pattern)
     */
    public void write(String text) {
        writingService.write(text, ink, stateManager, statistics);
    }
    
    /**
     * Close the pen cap
     */
    public void close() {
        if (stateManager.close()) {
            System.out.println("Pen closed successfully.");
        } else {
            System.out.println("Pen is already closed.");
        }
    }
    
    /**
     * Refill ink with new color
     */
    public void refill(String newInkColor) {
        ink.setColor(newInkColor);
        ink.refill();
        System.out.println("Pen refilled with " + newInkColor + " ink (" + ink.getType() + ")");
    }
    
    /**
     * Refill ink with current color
     */
    public void refill() {
        ink.refill();
        System.out.println("Pen refilled with " + ink.getColor() + " ink (" + ink.getType() + ")");
    }
    
    // Getters - expose only necessary information
    public PenState getState() {
        return stateManager.getState();
    }
    
    public double getInkQuantity() {
        return ink.getQuantity();
    }
    
    public String getInkColor() {
        return ink.getColor();
    }
    
    public String getInkType() {
        return ink.getType();
    }
    
    public String getBrand() {
        return brand;
    }
    
    public int getTotalWordsWritten() {
        return statistics.getTotalWordsWritten();
    }
    
    /**
     * Display complete pen status
     */
    public void displayStatus() {
        System.out.println("\n--- Pen Status ---");
        System.out.println("Brand: " + brand);
        System.out.println("State: " + stateManager.getState());
        System.out.println("Ink Type: " + ink.getType());
        System.out.println("Ink Color: " + ink.getColor());
        System.out.println("Ink Quantity: " + String.format("%.2f", ink.getQuantity()) + " ml");
        System.out.println("------------------");
        statistics.displayStatistics();
    }
}
