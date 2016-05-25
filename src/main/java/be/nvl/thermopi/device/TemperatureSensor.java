package be.nvl.thermopi.device;

import be.nvl.thermopi.dto.MetricsDTO;

/**
 * @author vanlooni
 * @since 0.1
 */
public interface TemperatureSensor {
    /**
     * Reads the Status, Humidity and Temperature data from the Honneywell sensor using the active i2c bus
     *
     * @return The temperature as BigDecimal
     */
    MetricsDTO read() throws Exception;
}
