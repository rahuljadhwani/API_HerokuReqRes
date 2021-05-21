Feature: To test the multipart abilities of Heroku web app using a file.

  @multipartPOST @upload @heroku
  Scenario: To test the upload functionality of Heroku webapp using multi-part POST request
    Given User has the base URI of "herokuapp"
    When User hits the server with "FileReader.txt" attached to POST request at endpoint "/upload"
    Then User should get the response code as 200


  @multipartPOST @download @heroku
  Scenario: To test the download functionality of Heroku webapp using multi-part POST request
    Given User has the base URI of "herokuapp"
    When User hits the server a GET request at endpoint "/download" with "FileReader.txt" file to download
    Then User should get the response code as 200