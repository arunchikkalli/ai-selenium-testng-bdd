Feature: Login Functionality
  As a user
  I want to login to the application
  So that I can access the dashboard

  @smoke @login
  Scenario: Successful login with valid credentials
    Given User navigates to login page
    When User enters username as "testuser"
    And User enters password as "password123"
    And User clicks login button
    Then User should see dashboard page

  @regression @login
  Scenario: Login with invalid username
    Given User navigates to login page
    When User enters username as "invaliduser"
    And User enters password as "password123"
    And User clicks login button
    Then User should see error message "Invalid username or password"

  @regression @login
  Scenario: Login with invalid password
    Given User navigates to login page
    When User enters username as "testuser"
    And User enters password as "wrongpassword"
    And User clicks login button
    Then User should see error message "Invalid username or password"

  @regression @login
  Scenario Outline: Login with multiple credentials
    Given User navigates to login page
    When User enters username as "<username>"
    And User enters password as "<password>"
    And User clicks login button
    Then User should see "<result>" message

    Examples:
      | username    | password      | result          |
      | testuser    | password123   | dashboard       |
      | invaliduser | wrongpass     | error message   |
      | emptyuser   | emptypass     | error message   |
