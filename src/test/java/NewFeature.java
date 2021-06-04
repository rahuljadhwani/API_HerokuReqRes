import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.Callable;

import static org.hamcrest.Matchers.*;

public class NewFeature extends PicoSharer{

    private PicoSharer picoSharer;

    public NewFeature(PicoSharer picoSharer){
        this.picoSharer = picoSharer;
    }


    RestAssured restAssured;
    Response response;
    @Given("I have baseURI as {string}")
    public void iHaveBaseURIAs(String baseURI) {
        restAssured.baseURI = baseURI;
    }

    @When("I hit API server with GET request with endpoint {string}")
    public void iHitAPIServerWithGETRequestWithEndpoint(String resourceID) {
        response = restAssured.given().when().get(resourceID);
        System.out.println("shared resource is::::::::::::::: "+picoSharer.resource);
    }

    @Then("status code is {int}")
    public void statusCodeIs(int arg0) {

        response.then().assertThat().body("data[0].id",equalTo(7));
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("data[2].email") instanceof String);
        System.out.println(JsonPath.from(response.asString()).get("data[2].first_name"));

        System.out.println("====================================================================");
        System.out.println(response.asString());
        System.out.println(response.then().extract().body().asString());
        System.out.println();
        Headers headers = response.then().extract().headers();
        System.out.println();
        Iterator<Header> iterator = headers.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println();
        System.out.println(response.then().extract().statusLine());
    }

}
