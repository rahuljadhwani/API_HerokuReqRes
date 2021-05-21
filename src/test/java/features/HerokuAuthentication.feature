Feature: To test different types of authentication on Heroku's app

  @basic_auth @authentication @heroku
  Scenario: To test basic authentication on Herokuapp
    Given User has the base URI of "herokuapp"
    When User hits the server with Get request with total resource "basic_auth" with "admin" as username and "admin" as password
    Then User should get the response code as 200

  @digest_auth @authentication @heroku
  Scenario: To test digest authentication on Herokuapp
    Given User has the base URI of "herokuapp"
    When User hits the server with Get request with total resource "digest_auth" with "admin" as username and "admin" as password
    Then User should get the response code as 200