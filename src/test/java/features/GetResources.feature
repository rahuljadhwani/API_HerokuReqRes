Feature: Get resources from reqres.in server

  Scenario Outline: Fetch a single resource by its ID
    Given User has the base URI of "reqres"
    When User hit the server with Get request with resource "api/unknown/"
    And resource unique id is <ID>
    Then User should get the response code as 200

    Examples:
    | ID |
    | 2 |
    | 3 |

    Scenario: Fetch the list of all the users
      Given User has the base URI of "reqres"
      When User hit the server with Get request with total resource "api/users?page=2"
      Then User should be able to fetch the list of users
      And User should get the response code as 200