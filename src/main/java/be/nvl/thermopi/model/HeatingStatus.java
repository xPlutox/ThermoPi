package be.nvl.thermopi.model;

import java.time.LocalDateTime;

/**
 * @author vanlooni
 * @since 0.1
 */
public class HeatingStatus {
    private boolean enabled;
    private LocalDateTime lastToggle;

    public HeatingStatus() {
        lastToggle = LocalDateTime.now();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getLastToggle() {
        return lastToggle;
    }

    public void setLastToggle(LocalDateTime lastToggle) {
        this.lastToggle = lastToggle;
    }
}
