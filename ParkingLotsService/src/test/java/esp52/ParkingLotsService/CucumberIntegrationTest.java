package esp52.ParkingLotsService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
//@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources", glue = {"com.whatever"})
//@CucumberOptions(features = "src/test/resources", plugin = {"pretty"}, publish = true)
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {"pretty", "html:target/cucumber/bagbasics"},
        extraGlue = "io.tpd.springbootcucumber.bagcommons")
public class CucumberIntegrationTest {

}
