package com.ai.selenium.listeners;

import com.ai.selenium.driver.DriverManager;
import com.ai.selenium.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("========================================");
        logger.info("Test Started: " + result.getName());
        logger.info("========================================");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test FAILED: " + result.getName());
        logger.error("Failure Reason: " + result.getThrowable());
        ScreenshotUtil.takeScreenshot(result.getName() + "_FAILED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test PASSED: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test SKIPPED: " + result.getName());
    }
}
