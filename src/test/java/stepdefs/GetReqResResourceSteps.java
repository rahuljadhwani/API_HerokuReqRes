package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.PropertyReader;

public class GetReqResResourceSteps {

    RequestSpecification requestSpecification;
    Response response;
    String baseURI;
    String responseString;
    String resourceID;

    @When("User hit the server with Get request with resource {string}")
    public void user_hit_the_server_with_get_request_with_resource(String resourceAddress) {
        requestSpecification = RestAssured.given();
        resourceID = resourceAddress;


    }
    @When("resource unique id is {int}")
    public void resource_unique_id_is(Integer resourceIDNum) {
        resourceID = resourceID+resourceIDNum;
        response = requestSpecification.when().get(resourceID);
        responseString = response.then().extract().body().asString();
        System.out.println(responseString);
        System.out.println("================");
        System.out.println(response.asString());

    }


    @Then("User should be able to fetch the list of users")
    public void user_should_be_able_to_fetch_the_list_of_users() {
        System.out.println(response.then().extract().body().asString());

    }

    //--------------------------------------------

    @Given("User has the base URI of {string}")
    public void user_has_the_base_uri_of(String baseURIName) {
        RestAssured.baseURI = PropertyReader.getPropertyByKey(baseURIName);
    }

    @When("User hit the server with Get request with total resource {string}")
    public void user_hit_the_server_with_get_request_with_total_resource(String resourceID) {
        response =  RestAssured.given().log().all().when().get(resourceID);
    }

    @When("User hit the server with Get request with total resource {string} with {string} as username and {string} as password")
    public void user_hit_the_server_with_get_request_with_total_resource_with_as_username_and_as_password(String resourceID, String username, String password) {
       response = RestAssured.given().log().all().auth().basic(username, password).when().get(resourceID);
    }

    @Then("User should get the response code as {int}")
    public void user_should_get_the_response_code_as(int statuscode) {
        Assert.assertEquals(statuscode, response.getStatusCode());
    }

}
