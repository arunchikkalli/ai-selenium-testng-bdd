package com.ai.selenium.base;

import com.ai.selenium.config.ConfigReader;
import com.ai.selenium.driver.DriverManager;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    /**
     * Wait for element to be visible and click it
     */
    public void click(By locator) {
        waitForElementVisibility(locator);
        driver.findElement(locator).click();
        logger.info("Clicked on element: " + locator);
    }

    /**
     * Send text to an element
     */
    public void sendKeys(By locator, String text) {
        WebElement element = waitForElementVisibility(locator);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text: '" + text + "' in element: " + locator);
    }

    /**
     * Get text from an element
     */
    public String getText(By locator) {
        return waitForElementVisibility(locator).getText();
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not found or not displayed: " + locator);
            return false;
        }
    }

    /**
     * Wait for element visibility
     */
    public WebElement waitForElementVisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (StaleElementReferenceException e) {
            logger.warn("Stale element reference, retrying...");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Execute JavaScript
     */
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }

    /**
     * Scroll to element
     */
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element: " + locator);
    }

    /**
     * Navigate to URL
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
        logger.info("Navigated to URL: " + url);
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
