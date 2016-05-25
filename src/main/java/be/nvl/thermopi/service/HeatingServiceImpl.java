package be.nvl.thermopi.service;

import be.nvl.thermopi.device.ThermoPiWrapper;
import be.nvl.thermopi.model.HeatingStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * todo: mechanism to prevent constzant switching: eg: delay with 4 seconds or no action and stop heating when too much toogle requests
 *
 * @author vanlooni
 * @since 0.1
 */
@Service
public class HeatingServiceImpl implements HeatingService {
    private Log log = LogFactory.getLog(HeatingServiceImpl.class);

    @Autowired
    private ThermoPiWrapper thermoPiWrapper;

    private HeatingStatus heatingStatus;

    @PostConstruct
    public void postConstruct() {
        heatingStatus = new HeatingStatus();
        heatingStatus.setEnabled(false);
    }

    @Override
    public boolean stopHeating() {
        log.info("Stopping heating");
        thermoPiWrapper.stopHeating();
        if (heatingStatus.isEnabled()) {
            heatingStatus.setEnabled(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean startHeating() {
        if (!heatingStatus.isEnabled()) {
            log.info("Starting heating");
            thermoPiWrapper.startHeating();
            heatingStatus.setEnabled(true);
            return true;
        } else {
            log.info("Heating already on, no need to start it again...");
            return false;
        }
    }

    @Override
    public void toggleHeating() {
        if (heatingStatus.isEnabled()) {
            stopHeating();
        } else {
            startHeating();
        }
    }

    @Override
    public HeatingStatus retrieveHeatingStatus() {
        return heatingStatus;
    }
}
