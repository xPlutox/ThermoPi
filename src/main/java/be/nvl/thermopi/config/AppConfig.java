package be.nvl.thermopi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * todo logback
 *
 * @author vanlooni
 * @version 0.1
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "be.nvl.thermopi")
public class AppConfig {
    public static final String PROFILE_LIVE = "live";
    public static final String PROFILE_TEST = "test";
}
