Feature: Creating a new user in Clear GRC

  Scenario: User should open login page of Clear GRC
    Given user can access the Clear GRC login page
    When user enters the valid emial Id
    And user enters the valid password in respective field from the input sheet
      | Excel      | Location                                  | Sheet | Index |
      | Createquestion.xlsx | C:\\Automation_Testing\\Input\\Createquestion.xlsx | Createquestion |     1 |
    Then user clicks submit button then it takes to homepage
    #Scenario: User should open the new user creation page
    Given user should click the Admin button
    And user could see all the options in this button
    When user clicks the User option
    Then it takes the user to user page.
    #Scenario: User should create a new user
    Given user click the add user button
    Then User should fill all the details of user
    And the user click the submit button
    And user click the Ok button
    Then new user has created
