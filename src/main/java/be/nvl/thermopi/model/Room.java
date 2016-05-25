package be.nvl.thermopi.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

/**
 * @author vanlooni
 * @since 23/05/2016
 */
public class Room {
    @Id
    private String id;
    private String name;
    private BigDecimal targetTemp;

    public Room(String name, BigDecimal targetTemp) {
        this.name = name;
        this.targetTemp = targetTemp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(BigDecimal targetTemp) {
        this.targetTemp = targetTemp;
    }
}
