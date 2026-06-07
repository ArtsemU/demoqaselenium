Feature: Buttons interactions on demoqa page

  Background:
    Given the user opens the page "https://demoqa.com/buttons"
    And   the page is scrolled down by 100 pixels

  Scenario: Double click on a button
    When  the user performs a double click on the button
    Then  the message "You have done a double click" is displayed

  Scenario: Right click on a button
    When  the user performs a right click on the button
    Then  the message "You have done a right click" is displayed

  Scenario: Dynamic click on a button
    When  the user performs a dynamic click on the button
    Then  the message "You have done a dynamic click" is displayed