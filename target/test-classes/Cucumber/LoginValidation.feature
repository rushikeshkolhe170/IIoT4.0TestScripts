@tag
Feature: Login page validations checking
  I want to use this template for my feature file

  @tag1
  Scenario: Required Validation Checking
    
    Given I landed on the login page of IIoT
    When I click on login without filling any details
    Then "Please ensure that the email and password entered are valid." toast message is displayed
    And "Email ID is required!" validation shows below the email field
    And "Password is required!" validation shows below the password field

  @tag2
  Scenario Outline: Incorrect Email Validation Checking
  
  	Given Enter invalid email "<email>" and password "<pass>" and verify "Invalid email format!" validation message
    
    Examples: 
      | email  | pass     |
      | abc123 | Test@123 |
    
#  @tag3
#  Scenario Outline: test
# 
#    Given I want to write a step with <name>
#    When I check for the <value> in step
#    Then I verify the <status> in step
#
#    Examples: 
#      | name  | value | status  |
#      | name1 |     5 | success |
#      | name2 |     7 | Fail    |
