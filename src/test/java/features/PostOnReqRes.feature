Feature: Post new resources to ReqRes.in server

  @post
  Scenario Outline:
    Given User has the base URI of "reqres"
    And has "CreateUser" request body attached with data "name" as "<name>" and "job" as "<job>"
    When User hits the POST request to server endpoint "api/users"
    Then User should get the response code as 201
    And resource should be added successfully

    Examples:
    | name | job |
    | Ray  | admin |
    |Jessica | HR  |
