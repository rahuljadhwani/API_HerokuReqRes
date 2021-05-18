package stepdefs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.PropertyReader;

public class ResourceSharer {

    private static RestAssured restAssured;
    private static Response response;

    public static void setRestAssuredBaseURI(String baseURIName){
        restAssured.baseURI = PropertyReader.getPropertyByKey(baseURIName);
    }

    public static RestAssured getRestAssuredResource(){
        return restAssured;
    }

    public static void setResponse(Response inputResponse){
        response = inputResponse;
    }

    public static Response getResponse(){
        return response ;
    }
}
