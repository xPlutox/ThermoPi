package be.nvl.thermopi.controller;

import be.nvl.thermopi.dto.ThermostatStatusDTO;
import be.nvl.thermopi.model.Metrics;
import be.nvl.thermopi.model.OperationFeedback;
import be.nvl.thermopi.model.Room;
import be.nvl.thermopi.service.HeatingService;
import be.nvl.thermopi.service.MetricsService;
import be.nvl.thermopi.service.ThermostatService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * todo target / room
 * todo metrics with websocket
 * todo input validation
 *
 * @author vanlooni
 * @since 0.1
 */
@RestController
@RequestMapping(value = "/api/thermostat")
public class ThermostatRestController {
    private Log log = LogFactory.getLog(ThermostatRestController.class);

    @Autowired
    private MetricsService metricsService;
    @Autowired
    private ThermostatService thermostatService;
    @Autowired
    private HeatingService heatingService;

    @RequestMapping(value = "room/get", method = RequestMethod.GET)
    @ResponseBody
    public ThermostatStatusDTO retrieveStatus() throws Exception {
        ThermostatStatusDTO thermostatStatusDTO = new ThermostatStatusDTO();
        thermostatStatusDTO.setHeatingStatus(heatingService.retrieveHeatingStatus());
        thermostatStatusDTO.setMetrics(metricsService.retrieveMetrics());
        return thermostatStatusDTO;
    }

    @RequestMapping(value = "target/set", method = RequestMethod.GET)
    @ResponseBody
    public OperationFeedback setTarget(@RequestParam(name = "target", defaultValue = "null") BigDecimal targetTemperature) {
        thermostatService.changeTargetTemperature(targetTemperature);
        return new OperationFeedback(true);
    }

    @RequestMapping(value = "metrics", method = RequestMethod.GET)
    @ResponseBody
    public Metrics retrieveMetrics() throws Exception {
        return metricsService.retrieveMetrics();
    }
}
