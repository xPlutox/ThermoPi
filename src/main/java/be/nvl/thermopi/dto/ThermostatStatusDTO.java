package be.nvl.thermopi.dto;

import be.nvl.thermopi.model.HeatingStatus;
import be.nvl.thermopi.model.Metrics;
import be.nvl.thermopi.model.Room;

import java.math.BigDecimal;

/**
 * @author vanlooni
 * @since 25/05/2016
 */
public class ThermostatStatusDTO {
    private BigDecimal targetTemperature;
    private HeatingStatus heatingStatus;
    private Metrics metrics;

    public ThermostatStatusDTO() {
    }

    public ThermostatStatusDTO(BigDecimal targetTemperature, HeatingStatus heatingStatus, Metrics metrics) {
        this.targetTemperature = targetTemperature;
        this.heatingStatus = heatingStatus;
        this.metrics = metrics;
    }

    public BigDecimal getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(BigDecimal targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public HeatingStatus getHeatingStatus() {
        return heatingStatus;
    }

    public void setHeatingStatus(HeatingStatus heatingStatus) {
        this.heatingStatus = heatingStatus;
    }
}
