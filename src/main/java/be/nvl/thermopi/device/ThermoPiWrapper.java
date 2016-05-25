package be.nvl.thermopi.device;

import be.nvl.thermopi.dto.MetricsDTO;

/**
 * @author vanlooni
 * @since 0.1
 */
public interface ThermoPiWrapper {
    void startHeating();

    void stopHeating();

    MetricsDTO read() throws Exception;
}
