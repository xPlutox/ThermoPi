package be.nvl.thermopi.model;

import be.nvl.thermopi.dto.MetricsDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author vanlooni
 * @since 0.1
 */
public class Metrics {
    private BigDecimal temperature;
    private BigDecimal humidity;
    private LocalDateTime time;

    public Metrics(MetricsDTO metricsDTO) {
        this.temperature = new BigDecimal(metricsDTO.getTemperature()).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.humidity = new BigDecimal(metricsDTO.getHumidity()).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.time = LocalDateTime.now();
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
