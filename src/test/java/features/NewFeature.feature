Feature: To play with response

  Scenario: play with response
    Given I have baseURI as "https://reqres.in/"
    When I hit API server with GET request with endpoint "/api/users?page=2"
    Then status code is 200