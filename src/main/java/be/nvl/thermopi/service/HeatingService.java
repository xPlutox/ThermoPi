package be.nvl.thermopi.service;

import be.nvl.thermopi.model.HeatingStatus;

/**
 * @author vanlooni
 * @since 0.1
 */
public interface HeatingService {
    boolean stopHeating();

    boolean startHeating();

    HeatingStatus retrieveHeatingStatus();

    void toggleHeating();
}
