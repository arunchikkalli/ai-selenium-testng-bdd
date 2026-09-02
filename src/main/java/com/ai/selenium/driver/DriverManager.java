package com.ai.selenium.driver;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.ai.selenium.config.ConfigReader;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver() {
        if (getDriver() == null) {
            WebDriver webDriver = DriverFactory.createDriver();
            driver.set(webDriver);

            // Set implicit wait
            webDriver.manage().timeouts().implicitlyWait(ConfigReader.getImplicitWait(), TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            // Maximize window
            webDriver.manage().window().maximize();

            logger.info("WebDriver initialized successfully");
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        WebDriver webDriver = getDriver();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
            logger.info("WebDriver quit successfully");
        }
    }
}
