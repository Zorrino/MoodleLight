Feature: Getting information

  Scenario: Getting the userId of an user
    Given a Student with the login "testStudent1"
    When the user "testStudent1" request his id
    Then the response is '^\{"id":[0-9]*\}$'

  Scenario: Getting the userName from an userId
    Given a Student with the login "testStudent1"
    When the user "testStudent1" request his username
    Then the response is '^\{"username":testStudent1\}$'

  Scenario: Getting the email from an userId
    Given a Student with the login "testStudent1"
    When the user "testStudent1" request his email
    Then the response is '^\{"email":testStudent1@test.fr\}$'

  Scenario: Getting the roles from an userId
    Given a Student with the login "testStudent1"
    When the user "testStudent1" request his roles
    Then the response is '^\[\{"id":[0-9]*, "name":ROLE_STUDENT\},\]|\[\]$'

  Scenario: Getting the modules from an userId
    Given a Student with the login "testStudent1"
    When the user "testStudent1" request his modules
    Then the response is '^\[\]$'

  Scenario: Getting the moduleId from a moduleName
    Given a Student with the login "testStudent1"
    And a Module named "testModule1"
    When "testStudent1" request the id of the module "testModule1"
    Then the response is '^\{"id":[0-9]*\}$'

  Scenario: Getting the moduleName from a moduleId
    Given a Student with the login "testStudent1"
    And a Module named "testModule1"
    When "testStudent1" request the name of the module "testModule1"
    Then the response is '^\{"name":testModule1\}$'

  Scenario: Getting the users from a moduleId
    Given a Student with the login "testStudent1"
    And a Module named "testModule1"
    When "testStudent1" request the users of the module "testModule1"
    Then the response is '^\[\]$'

  Scenario: Getting the resourceId from a resourceName
    Given a Student with the login "testStudent1"
    And a course named "testResource1"
    When "testStudent1" request the id of the resource "testResource1"
    Then the response is '^\{"id":[0-9]*\}$'

  Scenario: Getting the resourceName from a resourceId
    Given a Student with the login "testStudent1"
    And a course named "testResource1"
    When "testStudent1" request the name of the resource "testResource1"
    Then the response is '^\{"name":testResource1\}$'

  Scenario: Getting the resourceDescription from a resourceId
    Given a Teacher with the login "testTeacher1"
    And a course named "testResource1"
    When "testTeacher1" request the description of the resource "testResource1"
    Then the response is '^\{"description":test description\}$'

  Scenario: Getting the module from a resourceId
    Given a Teacher with the login "testTeacher1"
    And a course named "testResource1"
    When "testTeacher1" request the module of the resource "testResource1"
    Then the response is '^\{"module":testResource1\}|\{\}$'

  Scenario: Getting the resourceVisibility from a resourceId
    Given a Teacher with the login "testTeacher1"
    And a course named "testResource1"
    When "testTeacher1" request the visibility of the resource "testResource1"
    Then the response is '^\[\{"id":[0-9]*, "name":ROLE_TEACHER\}]$'

  Scenario: Getting the questionId from a questionName
    Given a Student with the login "testStudent1"
    And a open named "testQuestion1"
    When "testStudent1" request the id of question "testQuestion1"
    Then the response is '^\{"id":[0-9]*\}$'

  Scenario: Getting the questionName from a questionId
    Given a Student with the login "testStudent1"
    And a open named "testQuestion1"
    When "testStudent1" request the name of question "testQuestion1"
    Then the response is '^\{"name":testQuestion1\}$'

  Scenario: Getting the questionDescription from a questionName
    Given a Student with the login "testStudent1"
    And a open named "testQuestion1"
    When "testStudent1" request the description of question "testQuestion1"
    Then the response is '^\{"description":test description\}$'

  Scenario: Getting the answers for an open question from a questionName
    Given a Teacher with the login "testTeacher1"
    And a course named "testResource1"
    When "testTeacher1" request the multiple answers of question "testQuestion1"
    Then the response is '\[answer3, answer2, answer1\]'

  Scenario: Getting the correct answer from a questionName
    Given a Teacher with the login "testTeacher1"
    And a course named "testResource1"
    When "testTeacher1" request the mqc answer of question "testQuestion2"
    Then the response is '\[answer2\]'

  Scenario: Getting the questions of a questioner
    Given TODO

  Scenario: Getting the the answers from a user for a question
    Given TODO

  Scenario: Getting the score of a questioner
    Given TODO