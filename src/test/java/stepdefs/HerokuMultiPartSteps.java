package stepdefs;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.client.methods.RequestBuilder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HerokuMultiPartSteps {

    RestAssured restAssured;
    Response response;
    File file;

    @When("User hits the server with {string} attached to POST request at endpoint {string}")
    public void user_hits_the_server_with_attached_to_post_request_at_endpoint(String fileName, String resourceID) {
        restAssured = ResourceSharer.getRestAssuredResource();
        file = new File(System.getProperty("user.dir")+File.separator+fileName);
        response = restAssured.given().header("file","multipart/form-data").multiPart(file).post(resourceID);
        ResourceSharer.setResponse(response);
    }

    @When("User hits the server a GET request at endpoint {string} with {string} file to download")
    public void user_hits_the_server_a_get_request_at_endpoint_with_file_to_download(String resourceID, String filetoDownload) {
        restAssured = ResourceSharer.getRestAssuredResource();
        response = restAssured.given().when().get(resourceID+"/"+filetoDownload);
        writeToFile(filetoDownload);
        ResourceSharer.setResponse(response);

    }

    private void writeToFile(String downloadedFileName) {
        FileOutputStream fos = null;
        byte[] byteArray = response.then().extract().asByteArray();
        File file = new File(System.getProperty("user.dir")+File.separator+"downloaded"+downloadedFileName);
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            fos.write(byteArray);
            fos.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }





    }
}
