package be.nvl.thermopi.device;

import be.nvl.thermopi.dto.MetricsDTO;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * todo: implement mechanism that stops heating when there is no activity (hardware ...)
 *
 * @author vanlooni
 * @since 0.1
 */
public class ThermoPiWrapperImpl implements ThermoPiWrapper {
    private Log log = LogFactory.getLog(ThermoPiWrapperImpl.class);

    @Autowired
    private TemperatureSensor temperatureSensor;

    private GpioController gpioController;
    private GpioPinDigitalOutput heatingRelay;

    @PostConstruct
    public void postConstruct() {
        log.info("Initializing ThermoPi Wrapper. Heating default OFF");

        gpioController = GpioFactory.getInstance();
        heatingRelay = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Heating", PinState.LOW);
    }

    public void startHeating() {
        heatingRelay.high();
    }

    public void stopHeating() {
        heatingRelay.low();
    }

    @Override
    public MetricsDTO read() throws Exception {
        return temperatureSensor.read();
    }

    @PreDestroy
    public void destroy() {
        try {
            // Stop heating!!
            log.info("Tomcat shutdown, forcing heating to stop");

            stopHeating();

            if (gpioController != null) {
                gpioController.shutdown();
            }
        } catch (Exception e) {}
    }
}
