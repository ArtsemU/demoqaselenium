Feature: Practice form submission

  As a user of demoqa.com
  I want to fill in and submit the practice form
  So that the confirmation modal correctly reflects everything I submitted

  Background:
    Given the user opens the practice form page

  Scenario: Fill in every field of the practice form and verify the confirmation modal
    When the user enters first name "FirstName" and last name "LastName"
    And  the user enters email "email@gmail.com"
    And  the user selects gender "Male"
    And  the user enters mobile number "1234567890"
    And  the user sets date of birth to "01MAY2010"
    And  the user selects subject "English"
    And  the user selects hobbies "Sports" and "Reading"
    And  the user uploads the file "testFile1.txt"
    And  the user enters current address "Hello World!\nHave a good day!"
    And  the user selects state "NCR" and city "Noida"
    And  the user submits the form
    Then the confirmation title should be "Thanks for submitting the form"
    And  the submitted name should be "FirstName LastName"
    And  the submitted email should be "email@gmail.com"
    And  the submitted gender should be "Male"
    And  the submitted mobile number should be "1234567890"
    And  the submitted date of birth should match "01MAY2010"
    And  the submitted subject should be "English"
    And  the submitted hobbies should be "Sports, Reading"
    And  the submitted file name should be "testFile1.txt"
    And  the submitted address should be "Hello World! Have a good day!"
    And  the submitted state and city should be "NCR Noida"
