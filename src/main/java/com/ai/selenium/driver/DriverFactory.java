package com.ai.selenium.driver;

import com.ai.selenium.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        logger.info("Creating WebDriver for browser: " + browser);

        if (ConfigReader.isRemote()) {
            return createRemoteDriver(browser);
        } else {
            return createLocalDriver(browser);
        }
    }

    private static WebDriver createLocalDriver(String browser) {
        switch (browser) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Chrome started in headless mode");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1920,1080");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);

        logger.info("Chrome WebDriver created successfully");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Firefox started in headless mode");
        }

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        logger.info("Firefox WebDriver created successfully");
        return new FirefoxDriver(options);
    }

    private static WebDriver createRemoteDriver(String browser) {
        try {
            String remoteUrl = ConfigReader.getRemoteUrl();
            logger.info("Creating remote WebDriver at: " + remoteUrl);

            if ("chrome".equals(browser)) {
                ChromeOptions options = new ChromeOptions();
                return new RemoteWebDriver(new URL(remoteUrl), options);
            } else if ("firefox".equals(browser)) {
                FirefoxOptions options = new FirefoxOptions();
                return new RemoteWebDriver(new URL(remoteUrl), options);
            }
        } catch (MalformedURLException e) {
            logger.error("Invalid remote URL: " + ConfigReader.getRemoteUrl(), e);
        }

        throw new RuntimeException("Failed to create remote WebDriver");
    }
}
