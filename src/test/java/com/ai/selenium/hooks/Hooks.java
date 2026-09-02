package com.ai.selenium.hooks;

import com.ai.selenium.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        logger.info("\n\n ========== Test Execution Started ==========");
        DriverManager.initializeDriver();
        logger.info("WebDriver initialized");
    }

    @After
    public void tearDown() {
        logger.info("Closing WebDriver...");
        DriverManager.quitDriver();
        logger.info("========== Test Execution Finished ==========");
    }
}
