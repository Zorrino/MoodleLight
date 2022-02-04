Feature: Logging as a User
  Background :
    Given Authentication Service
    And A User named "Matthieu"
    And his login is "mathieu01"
    And his password is "azertyuiop"

  Scenario : Logging to the authentication service with a valid token
    When User has a valid Token
    Then Authentication Service compare token
    And He can connect to the Service

  Scenario : Logging to the authentication service without a valid token
    When User doesn't have a valid Token
    Then Authentication Service compare token
    And User can't Connect to the Service
    And Show error 401 to User
    And Authentication Service asks User to gives his password

  Scenario: "Matthieu" enter a wrong login
    When "Matthieu" try to enter the wrong login "falseLogin"
    Then last request status is 400
    And "Mathieu" is don't login

  Scenario: "Matthieu" enter a wrong password
    When "Matthieu" try to enter the wrong password "poiuytreza"
    Then last request status is 400
    And "Mathieu" is don't login