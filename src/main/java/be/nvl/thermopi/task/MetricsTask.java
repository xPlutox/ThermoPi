package be.nvl.thermopi.task;

import be.nvl.thermopi.model.Metrics;
import be.nvl.thermopi.service.MetricsService;
import be.nvl.thermopi.service.ThermostatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * todo make timing dynamic configurable
 * todo work with event listeners
 *
 * @author vanlooni
 * @since 23/05/2016
 */
@Component
public class MetricsTask {
    private Log log = LogFactory.getLog(MetricsTask.class);

    @Autowired
    private MetricsService metricsService;

    @Autowired
    private ThermostatService thermostatService;

    @Scheduled(fixedRate = 10000)
    public void checkMetrics() throws Exception {
        log.info("Fetching metrics");
        metricsService.updateMetrics();
        thermostatService.checkTargetTemp();
    }
}
