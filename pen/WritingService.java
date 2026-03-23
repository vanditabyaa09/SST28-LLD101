/**
 * Interface for writing operations
 * Abstraction for different writing strategies
 * Follows Interface Segregation Principle - clients depend only on write operation
 */
public interface WritingService {
    /**
     * Write text using the provided ink and state manager
     */
    void write(String text, Ink ink, PenStateManager stateManager, PenStatistics statistics);
    
    /**
     * Get service name
     */
    String getServiceName();
}
