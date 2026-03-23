/**
 * Tracks pen usage statistics
 * Single Responsibility: Only responsible for tracking statistics
 * Follows Single Responsibility Principle
 */
public class PenStatistics {
    private int totalWordsWritten;
    private int totalWritingOperations;
    private long totalCharactersWritten;
    
    public PenStatistics() {
        this.totalWordsWritten = 0;
        this.totalWritingOperations = 0;
        this.totalCharactersWritten = 0;
    }
    
    /**
     * Record a write operation
     */
    public void recordWrite(String text) {
        int wordCount = text.split("\\s+").length;
        this.totalWordsWritten += wordCount;
        this.totalWritingOperations++;
        this.totalCharactersWritten += text.length();
    }
    
    /**
     * Get total words written
     */
    public int getTotalWordsWritten() {
        return totalWordsWritten;
    }
    
    /**
     * Get total writing operations
     */
    public int getTotalWritingOperations() {
        return totalWritingOperations;
    }
    
    /**
     * Get total characters written
     */
    public long getTotalCharactersWritten() {
        return totalCharactersWritten;
    }
    
    /**
     * Reset statistics
     */
    public void reset() {
        this.totalWordsWritten = 0;
        this.totalWritingOperations = 0;
        this.totalCharactersWritten = 0;
    }
    
    /**
     * Display statistics summary
     */
    public void displayStatistics() {
        System.out.println("--- Writing Statistics ---");
        System.out.println("Total Words Written: " + totalWordsWritten);
        System.out.println("Total Writing Operations: " + totalWritingOperations);
        System.out.println("Total Characters Written: " + totalCharactersWritten);
        System.out.println("--------------------------");
    }
}
