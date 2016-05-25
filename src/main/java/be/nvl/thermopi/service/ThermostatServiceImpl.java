package be.nvl.thermopi.service;

import be.nvl.thermopi.model.Metrics;
import be.nvl.thermopi.model.Room;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * todo facade?
 * todo multiple room system
 * todo make room config persistent
 *
 * @author vanlooni
 * @since 23/05/2016
 */
@Service
public class ThermostatServiceImpl implements ThermostatService {
    private Log log = LogFactory.getLog(ThermostatServiceImpl.class);

    private static final BigDecimal TEMP_TRESHOLD = new BigDecimal(0.2D);

    @Autowired
    private HeatingService heatingService;
    @Autowired
    private MetricsService metricsService;

    private Room room;

    @PostConstruct
    public void postConstruct() {
        // temp. initialise one room
        this.room = new Room("De Living", new BigDecimal(20));
    }

    @Override
    public Room retrieveRoom() throws Exception {
        return room;
    }

    @Override
    public void changeTargetTemperature(BigDecimal targetTemp) {
        room.setTargetTemp(targetTemp);
    }

    @Override
    public void checkTargetTemp() throws Exception {
        Metrics metrics = metricsService.retrieveMetrics();
        BigDecimal min = room.getTargetTemp().subtract(TEMP_TRESHOLD).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal max = room.getTargetTemp().add(TEMP_TRESHOLD).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (metrics.getTemperature().compareTo(min) < 0) {
            heatingService.startHeating();
        } else if (metrics.getTemperature().compareTo(max) > 0) {
            heatingService.stopHeating();
        }
    }
}
