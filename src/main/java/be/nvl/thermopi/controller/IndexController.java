package be.nvl.thermopi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vanlooni
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex() {
        return "index";
    }

}