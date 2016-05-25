package be.nvl.thermopi.config;

import be.nvl.thermopi.device.TemperatureSensor;
import be.nvl.thermopi.device.TemperatureSensorMockImpl;
import be.nvl.thermopi.device.ThermoPiWrapper;
import be.nvl.thermopi.device.ThermoPiWrapperMockImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author vanlooni
 * @since 0.1
 */
@Profile(AppConfig.PROFILE_TEST)
@Configuration
public class ThermoPiTestConfig {
    @Bean
    @Primary
    public ThermoPiWrapper thermoPiWrapper() {
        return new ThermoPiWrapperMockImpl();
    }

    @Bean
    @Primary
    public TemperatureSensor temperatureSensor() {
        return new TemperatureSensorMockImpl();
    }
}
