package be.nvl.thermopi.dto;

/**
 * @author vanlooni
 * @since 23/05/2016
 */
public class MetricsDTO {
    private float temperature;
    private float humidity;

    public static MetricsDTO build(float temperature, float humidity) {
        MetricsDTO metricsDTO = new MetricsDTO();
        metricsDTO.setTemperature(temperature);
        metricsDTO.setHumidity(humidity);
        return metricsDTO;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
