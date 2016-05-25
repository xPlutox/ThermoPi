package be.nvl.thermopi.device;

import be.nvl.thermopi.dto.MetricsDTO;
import be.nvl.thermopi.util.StringUtil;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * i2c Connection to the Honneywell Honeywell HIH6130-021-001 (compensated) sensor
 *
 * todo: keep bus IO connection open?
 * todo: humidity
 * todo: caching, event driven
 * todo: status check & feedback
 *
 * @author vanlooni
 * @since 0.1
 */
public class TemperatureSensorImpl implements TemperatureSensor {
    private Log log = LogFactory.getLog(TemperatureSensorImpl.class);

    // Raspberry Pi's I2C bus
    private static final int RPI_I2C_BUS = I2CBus.BUS_1;
    // Sensor i2c slave address, Honneywells default: 27
    private static final int SENSOR_I2C_ADDRESS = 0x27;

    private I2CBus bus;
    private I2CDevice sensor;

    @PostConstruct
    public void postConstruct() {
        log.info("Opening connection to the i2c bus");

        try {
            bus = I2CFactory.getInstance(RPI_I2C_BUS);
            log.debug("Connection to i2c bus OK!!!");

            // Get the sensor
            sensor = bus.getDevice(SENSOR_I2C_ADDRESS);
            log.debug("Connection to sensor OK!!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MetricsDTO read() throws Exception {
        // Start measurement
        log.debug("Starting measurements (i2c write)");
        sensor.write((byte) 0);

        // Wait for the measurement results
        Thread.sleep(500);

        // Read data (4 bytes)
        byte[] data = new byte[4];

        log.debug("Fetching new measurements (i2c connect 4 bytes)");
        int totalBytesRead = sensor.read(data, 0, 4);
        if (totalBytesRead < 4) {
            throw new RuntimeException("Error: " + totalBytesRead + " bytes connect, expected: 4");
        }

        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(data));
        byte data1 = inputStream.readByte();
        byte data2 = inputStream.readByte();
        byte data3 = inputStream.readByte();
        byte data4 = inputStream.readByte();
        log.info("Data1 output: " + StringUtil.formatByteAsBit(data1));
        log.info("Data2 output: " + StringUtil.formatByteAsBit(data2));
        log.info("Data3 output: " + StringUtil.formatByteAsBit(data3));
        log.info("Data4 output: " + StringUtil.formatByteAsBit(data4));

        float resultTempCounts = (data3 * 256 + data4) >> 2;
        log.info("Result temp: " + resultTempCounts);

        float humid = ((float)(data1 & 0x3f) * 256 + data2) / 16383 * 100;
        float temp = ((resultTempCounts / 16383) * 165) - 40;

        log.debug("Status: " + String.valueOf((data1 & 0xc0) >> 6));
        log.debug("Real humid: " + humid);
        log.debug("Real temp: " + temp);
        return MetricsDTO.build(temp, humid);
    }

    @PreDestroy
    private void destroy() {
        // Close the connection to the sensor
        if (bus != null) {
            try {
                bus.close();
            } catch (IOException e) {
            }
        }
    }
}