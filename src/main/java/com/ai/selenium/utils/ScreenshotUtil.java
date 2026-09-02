package com.ai.selenium.utils;

import com.ai.selenium.config.ConfigReader;
import com.ai.selenium.driver.DriverManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    public static String takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            logger.warn("WebDriver is null, cannot take screenshot");
            return null;
        }

        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = ConfigReader.getScreenshotPath() + File.separator + fileName;

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);

            // Create directory if it doesn't exist
            destinationFile.getParentFile().mkdirs();
            org.apache.commons.io.FileUtils.copyFile(screenshotFile, destinationFile);

            logger.info("Screenshot saved: " + filePath);
            return filePath;
        } catch (Exception e) {
            logger.error("Failed to take screenshot", e);
            return null;
        }
    }
}
