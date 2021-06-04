import io.cucumber.java.en.Given;

public class NewFeature2 extends PicoSharer{

    private PicoSharer picoSharer;

    public NewFeature2(PicoSharer picoSharer){
        this.picoSharer = picoSharer;
    }

    @Given("I have baseURI {string}")
    public void iHaveBaseURI(String arg0) {
        picoSharer.resource = arg0;
    }
}
