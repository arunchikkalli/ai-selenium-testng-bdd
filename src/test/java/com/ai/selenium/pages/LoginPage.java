package com.ai.selenium.pages;

import com.ai.selenium.base.BasePage;
import com.ai.selenium.config.ConfigReader;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    // Locators
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("loginBtn");
    private static final By ERROR_MESSAGE = By.className("error-message");
    private static final By DASHBOARD_ELEMENT = By.id("dashboard");

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateTo(ConfigReader.getBaseUrl());
        waitForElementVisibility(USERNAME_FIELD);
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        sendKeys(USERNAME_FIELD, username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        sendKeys(PASSWORD_FIELD, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    /**
     * Check if login was successful
     */
    public boolean isLoginSuccessful() {
        return isElementDisplayed(DASHBOARD_ELEMENT);
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    /**
     * Login with username and password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
