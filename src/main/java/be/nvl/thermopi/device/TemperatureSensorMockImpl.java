package be.nvl.thermopi.device;

import be.nvl.thermopi.dto.MetricsDTO;

/**
 * @author vanlooni
 * @since 0.1
 */
public class TemperatureSensorMockImpl implements TemperatureSensor {
    @Override
    public MetricsDTO read() throws Exception {
        return new MetricsDTO();
    }
}
