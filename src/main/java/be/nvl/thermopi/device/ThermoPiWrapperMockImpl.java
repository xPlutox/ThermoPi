package be.nvl.thermopi.device;

import be.nvl.thermopi.dto.MetricsDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Random;

/**
 * @author vanlooni
 * @since 0.1
 */
public class ThermoPiWrapperMockImpl implements ThermoPiWrapper {
    private Log log = LogFactory.getLog(ThermoPiWrapperMockImpl.class);

    private float currentTemp = 18F;

    @Override
    public void startHeating() {
        log.info("ThermoPi MOCK: Start heating");
    }

    @Override
    public void stopHeating() {
        log.info("ThermoPi MOCK: Stop heating");
    }

    @Override
    public MetricsDTO read() {
        if (new Random().nextBoolean()) {
            currentTemp += 0.2F;
        } else {
            currentTemp -= 0.2F;
        }
        return MetricsDTO.build(currentTemp, 60.2F);
    }
}
