/**
 * Created by camerongera on 11/13/16.
 */

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty",
                "json: testFeatures/cucumber.json" },
        features = "testFeatures/"
)
public class AcceptanceTestSuite {
}
