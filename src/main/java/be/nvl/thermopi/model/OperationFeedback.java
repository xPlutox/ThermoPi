package be.nvl.thermopi.model;

/**
 * @author vanlooni
 * @since 0.1
 */
public class OperationFeedback {
    boolean success;

    public OperationFeedback(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
