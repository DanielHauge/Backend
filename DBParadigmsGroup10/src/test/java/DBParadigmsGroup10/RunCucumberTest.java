package DBParadigmsGroup10;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"} /*, features = "src/test/java/DBParadigmsGroup10", glue = "src/test/java/Interfaces/MyStepdefs.java" */)
public class RunCucumberTest {
}
