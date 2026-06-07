Feature: API smoke tests

  Scenario: Google homepage returns 200
    Given I send a GET request to "https://www.google.com"
    Then the response status code should be 200