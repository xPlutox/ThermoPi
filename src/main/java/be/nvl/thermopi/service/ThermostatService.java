package be.nvl.thermopi.service;

import be.nvl.thermopi.model.Room;

import java.math.BigDecimal;

/**
 * @author vanlooni
 * @since 23/05/2016
 */
public interface ThermostatService {

    Room retrieveRoom() throws Exception;

    void changeTargetTemperature(BigDecimal targetTemp);

    void checkTargetTemp() throws Exception;
}
