package com.ai.selenium.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static JsonObject configData;
    private static final String CONFIG_PATH = "src/test/resources/config.json";

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            Gson gson = new Gson();
            configData = gson.fromJson(new FileReader(CONFIG_PATH), JsonObject.class);
            logger.info("Configuration loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + CONFIG_PATH, e);
            throw new RuntimeException("Configuration file not found at: " + CONFIG_PATH);
        }
    }

    public static String getBaseUrl() {
        return configData.get("baseUrl").getAsString();
    }

    public static String getBrowser() {
        return configData.get("browser").getAsString();
    }

    public static boolean isHeadless() {
        return configData.get("headless").getAsBoolean();
    }

    public static int getImplicitWait() {
        return configData.get("implicitWait").getAsInt();
    }

    public static int getExplicitWait() {
        return configData.get("explicitWait").getAsInt();
    }

    public static String getScreenshotPath() {
        return configData.get("screenshotPath").getAsString();
    }

    public static boolean isRemote() {
        return configData.get("remote").getAsBoolean();
    }

    public static String getRemoteUrl() {
        return configData.get("remoteUrl").getAsString();
    }
}
