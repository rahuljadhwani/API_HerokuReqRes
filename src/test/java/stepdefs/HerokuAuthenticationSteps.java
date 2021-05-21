package stepdefs;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;


public class HerokuAuthenticationSteps {

    static Logger logger = Logger.getLogger(HerokuAuthenticationSteps.class);
    Response response;

    @When("User hits the server with Get request with total resource {string} with {string} as username and {string} as password")
    public void user_hits_the_server_with_get_request_with_total_resource_with_as_username_and_as_password(String resourceID, String username, String password) {
        if(resourceID.toLowerCase().contains("digest")){
            response = ResourceSharer.getRestAssuredResource().given().log().all().auth().digest(username, password).when().get(resourceID);
            logger.info("User is using digest authentication to acess Heroku app.");
        }
        else { response = ResourceSharer.getRestAssuredResource().given().log().all().auth().basic(username, password).when().get(resourceID);
        logger.info("User is using basic authentication to acess Heroku app.");
        }
        ResourceSharer.setResponse(response);
    }
}
