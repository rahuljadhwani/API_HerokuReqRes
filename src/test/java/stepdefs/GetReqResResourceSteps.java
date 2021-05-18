package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.Assert;
import utils.PropertyReader;

import javax.annotation.Resource;


public class GetReqResResourceSteps {

    static Logger logger = Logger.getLogger(GetReqResResourceSteps.class);

    RestAssured restAssured;
    RequestSpecification requestSpecification;
    Response response;
    String baseURI;
    String responseString;
    String resourceID;

    @When("User hit the server with Get request with resource {string}")
    public void user_hit_the_server_with_get_request_with_resource(String resourceAddress) {
        requestSpecification = ResourceSharer.getRestAssuredResource().given();
        resourceID = resourceAddress;
    }
    @When("resource unique id is {int}")
    public void resource_unique_id_is(Integer resourceIDNum) {
        resourceID = resourceID+resourceIDNum;
        response = requestSpecification.when().get(resourceID);
        ResourceSharer.setResponse(response);
        responseString = response.then().extract().body().asString();
        System.out.println(responseString);

    }


    @Then("User should be able to fetch the list of users")
    public void user_should_be_able_to_fetch_the_list_of_users() {
        System.out.println(response.then().extract().body().asString());

    }

    //--------------------------------------------

    @Given("User has the base URI of {string}")
    public void user_has_the_base_uri_of(String baseURIName) {
        ResourceSharer.setRestAssuredBaseURI(baseURIName);
        logger.info("User has base URI as "+baseURIName);
    }

    @When("User hit the server with Get request with total resource {string}")
    public void user_hit_the_server_with_get_request_with_total_resource(String resourceID) {
        response =   ResourceSharer.getRestAssuredResource().given().log().all().when().get(resourceID);
        ResourceSharer.setResponse(response);
    }

    @Then("User should get the response code as {int}")
    public void user_should_get_the_response_code_as(int statuscode) {
        response = ResourceSharer.getResponse();

        if(response.getStatusCode()==statuscode){
            Assert.assertTrue(true);
            logger.info("API has sent the status code as: "+statuscode);
        } else {
            logger.error("API has responded with status code: "+statuscode);
            Assert.fail();

        }
    }

}
