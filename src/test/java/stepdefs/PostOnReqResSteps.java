package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import utils.JSONHandler;

import java.util.HashMap;
import java.util.Map;

import static utils.JSONHandler.getJsonHandlerObject;

public class PostOnReqResSteps {

    RestAssured restAssured;
    RequestSpecification requestSpecification;
    Response response;
    JSONHandler jsonHandler;
    String fieldValidator;

    @Given("has {string} request body attached with data {string} as {string} and {string} as {string}")
    public void has_request_body_attached_with_data_as_and_as(String fileName, String name, String namevalue, String job, String jobvalue) {
        restAssured = ResourceSharer.getRestAssuredResource();
        jsonHandler = getJsonHandlerObject();
        Map<String, Object> fieldMap = new HashMap();
        fieldMap.put(name, namevalue);
        fieldMap.put(job, jobvalue);
        JSONObject updatedObject = jsonHandler.getUpdatedJSONObject(jsonHandler.loadJSONFile(fileName), fieldMap);
        System.out.println(updatedObject.toString());
        requestSpecification = restAssured.given().log().all().body(updatedObject);
    }

    @When("User hits the POST request to server endpoint {string}")
    public void user_hits_the_post_request_to_server_endpoint(String resourceId) {
        response = requestSpecification.when().post(resourceId);
        ResourceSharer.setResponse(response);
    }

    @Then("resource should be added successfully")
    public void resource_should_be_added_successfully() {
        System.out.println(response.asString());
        fetchResource("id");
        Assert.assertTrue(response.asString().contains("id"));
    }

    private void fetchResource(String id) {
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString(id));
    }
}
