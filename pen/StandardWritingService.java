/**
 * Standard Writing Service Implementation
 * Single Responsibility: Handles writing operation logic
 * Follows Open/Closed Principle: Can be extended for different writing strategies
 */
public class StandardWritingService implements WritingService {
    
    @Override
    public void write(String text, Ink ink, PenStateManager stateManager, PenStatistics statistics) {
        // Validate preconditions
        if (stateManager.isClosed()) {
            System.out.println("Cannot write! Pen is closed. Please call start() first.");
            return;
        }
        
        if (ink.isEmpty()) {
            System.out.println("Cannot write! Ink is empty. Please refill().");
            return;
        }
        
        // Perform write operation
        stateManager.startWriting();
        int wordCount = text.split("\\s+").length;
        
        System.out.println("Writing: \"" + text + "\"");
        ink.consume(wordCount);
        statistics.recordWrite(text);
        
        System.out.println("Written " + wordCount + " words. Remaining ink: " + 
                          String.format("%.2f", ink.getQuantity()) + " ml");
    }
    
    @Override
    public String getServiceName() {
        return "Standard Writing Service";
    }
}
