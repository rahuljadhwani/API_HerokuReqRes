package testrunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "src/test/java/features", glue = "stepdefs", dryRun = false, monochrome = true, plugin = {"html:target/report.html", "json:target/report.json"})
public class TestRunner {
}
