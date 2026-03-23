/**
 * Main class demonstrating SOLID principles in Pen design
 * Uses Dependency Injection and Factory Pattern
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== SOLID Pen System - LLD Design =====\n");
        
        // Example 1: Ballpoint Pen
        demonstrateBallpointPen();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Example 2: Gel Pen
        demonstrateGelPen();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Example 3: Different Ink Type with Same Pen Architecture
        demonstratePenExtensibility();
    }
    
    /**
     * Demonstrates ballpoint pen usage
     */
    private static void demonstrateBallpointPen() {
        System.out.println("--- Example 1: Ballpoint Pen ---\n");
        
        // Dependency Injection - can change ink and writing service without modifying Pen class
        Ink ballpointInk = new BallpointInk(5.0, "Blue");
        WritingService writingService = new StandardWritingService();
        Pen ballpointPen = new Pen("Ballpoint Brand", ballpointInk, writingService);
        
        // Test operations
        System.out.println("Test 1: Try to write without opening");
        ballpointPen.write("This should not work");
        System.out.println();
        
        System.out.println("Test 2: Open the pen");
        ballpointPen.start();
        System.out.println();
        
        System.out.println("Test 3: Write text");
        ballpointPen.write("Hello from Ballpoint Pen");
        System.out.println();
        
        System.out.println("Test 4: Write more text");
        ballpointPen.write("SOLID principles in action");
        System.out.println();
        
        System.out.println("Test 5: Close the pen");
        ballpointPen.close();
        System.out.println();
        
        System.out.println("Test 6: Display status");
        ballpointPen.displayStatus();
    }
    
    /**
     * Demonstrates gel pen with higher ink consumption
     */
    private static void demonstrateGelPen() {
        System.out.println("--- Example 2: Gel Pen (Different Ink Type) ---\n");
        
        // Same pen architecture works with different ink implementation
        Ink gelInk = new GelInk(4.0, "Red");
        WritingService writingService = new StandardWritingService();
        Pen gelPen = new Pen("Gel Super", gelInk, writingService);
        
        gelPen.start();
        System.out.println("Test: Writing with Gel Ink (consumes more ink)");
        gelPen.write("Gel pens have higher flow rate");
        System.out.println();
        
        gelPen.write("Notice more ink consumption than ballpoint");
        System.out.println();
        
        gelPen.displayStatus();
    }
    
    /**
     * Demonstrates extensibility - showing how easy it is to add new ink types
     */
    private static void demonstratePenExtensibility() {
        System.out.println("--- Example 3: System Extensibility ---\n");
        System.out.println("The pen architecture supports:");
        System.out.println("✓ New Ink Types: Just implement the Ink interface");
        System.out.println("✓ New Writing Strategies: Implement WritingService interface");
        System.out.println("✓ Dependency Injection: No modification to Pen class needed");
        System.out.println();
        
        // Demonstrating interchangeability
        Ink ink1 = new BallpointInk(5.0, "Blue");
        Ink ink2 = new GelInk(4.0, "Green");
        WritingService service = new StandardWritingService();
        
        Pen pen1 = new Pen("Brand A", ink1, service);
        Pen pen2 = new Pen("Brand B", ink2, service);
        
        System.out.println("Pen 1 (Ballpoint): Ink Type = " + pen1.getInkType());
        System.out.println("Pen 2 (Gel): Ink Type = " + pen2.getInkType());
        System.out.println();
        System.out.println("Same Pen class, different behaviors!");
        System.out.println("This is the power of SOLID principles and Dependency Injection.");
    }
}
