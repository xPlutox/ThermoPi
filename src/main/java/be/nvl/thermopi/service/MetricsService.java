package be.nvl.thermopi.service;

import be.nvl.thermopi.model.Metrics;

/**
 * @author vanlooni
 * @since 0.1
 */
public interface MetricsService {
    Metrics retrieveMetrics() throws Exception;

    void updateMetrics() throws Exception;
}
