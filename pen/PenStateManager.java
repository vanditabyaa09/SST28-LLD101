/**
 * Manages Pen State and Transitions
 * Single Responsibility: Only responsible for state management
 * Follows Open/Closed Principle: Can be extended for new states
 */
public class PenStateManager {
    private PenState currentState;
    
    public PenStateManager() {
        this.currentState = PenState.CLOSED;
    }
    
    /**
     * Open the pen (transition from CLOSED to OPEN)
     */
    public boolean open() {
        if (currentState == PenState.CLOSED) {
            currentState = PenState.OPEN;
            return true;
        }
        return false;
    }
    
    /**
     * Close the pen
     */
    public boolean close() {
        if (currentState != PenState.CLOSED) {
            currentState = PenState.CLOSED;
            return true;
        }
        return false;
    }
    
    /**
     * Start writing (transition to WRITING state)
     */
    public boolean startWriting() {
        if (currentState == PenState.OPEN || currentState == PenState.WRITING) {
            currentState = PenState.WRITING;
            return true;
        }
        return false;
    }
    
    /**
     * Check if pen can write
     */
    public boolean canWrite() {
        return currentState == PenState.OPEN || currentState == PenState.WRITING;
    }
    
    /**
     * Check if pen is closed
     */
    public boolean isClosed() {
        return currentState == PenState.CLOSED;
    }
    
    /**
     * Get current state
     */
    public PenState getState() {
        return currentState;
    }
}
