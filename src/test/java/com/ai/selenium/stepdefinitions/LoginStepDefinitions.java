package com.ai.selenium.stepdefinitions;

import com.ai.selenium.base.BasePage;
import com.ai.selenium.driver.DriverManager;
import com.ai.selenium.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LoginStepDefinitions extends BasePage {
    private static final Logger logger = LogManager.getLogger(LoginStepDefinitions.class);
    private LoginPage loginPage;

    @Given("User navigates to login page")
    public void user_navigates_to_login_page() {
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
        logger.info("User navigated to login page");
    }

    @When("User enters username as {string}")
    public void user_enters_username(String username) {
        loginPage.enterUsername(username);
        logger.info("Username entered: " + username);
    }

    @And("User enters password as {string}")
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
        logger.info("Password entered");
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
        logger.info("Login button clicked");
    }

    @Then("User should see dashboard page")
    public void user_should_see_dashboard() {
        boolean isLoggedIn = loginPage.isLoginSuccessful();
        Assert.assertTrue(isLoggedIn, "Login was not successful");
        logger.info("User successfully logged in");
    }

    @Then("User should see error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch");
        logger.info("Error message verified: " + actualMessage);
    }
}
