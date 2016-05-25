package be.nvl.thermopi.controller;

import be.nvl.thermopi.model.OperationFeedback;
import be.nvl.thermopi.service.HeatingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo exception handling: status to frontend
 *
 * @author vanlooni
 * @since 0.1
 */
@RestController
@RequestMapping(value = "/api/heating")
public class HeatingRestController {
    private Log log = LogFactory.getLog(HeatingRestController.class);

    @Autowired
    private HeatingService heatingService;

    @RequestMapping(value = "switch", method = RequestMethod.GET)
    public void startHeating(@RequestParam(name = "enabled", defaultValue = "false") boolean enabled) {
        if (enabled) {
            heatingService.startHeating();
        } else {
            heatingService.stopHeating();
        }
    }

    @RequestMapping(value = "toggle", method = RequestMethod.GET)
    @ResponseBody
    public OperationFeedback toggleHeating() {
        try {
            heatingService.toggleHeating();
            return new OperationFeedback(true);
        } catch (Exception e) {
            log.error("", e);
            return new OperationFeedback(false);
        }
    }

}
