package be.nvl.thermopi.service;

import be.nvl.thermopi.device.ThermoPiWrapper;
import be.nvl.thermopi.model.Metrics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * todo event listener
 *
 * @author vanlooni
 * @since 0.1
 */
@Service
public class MetricsServiceImpl implements MetricsService {
    private Log log = LogFactory.getLog(MetricsServiceImpl.class);

    private Metrics metrics;

    @Autowired
    private ThermoPiWrapper thermoPiWrapper;

    @Override
    public Metrics retrieveMetrics() throws Exception {
        return metrics; // todo synchronise
    }

    @Override
    public void updateMetrics() throws Exception {
        metrics = new Metrics(thermoPiWrapper.read()); // todo synchronise
    }
}
