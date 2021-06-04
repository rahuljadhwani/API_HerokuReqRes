Feature: To play with response2

  Scenario: play with response
    Given I have baseURI "https://reqres.in/"
    When I hit API server with GET request with endpoint "/api/users?page=2"
    Then status code is 200