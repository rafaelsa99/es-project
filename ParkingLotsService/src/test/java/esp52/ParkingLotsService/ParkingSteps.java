package esp52.ParkingLotsService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

public class ParkingSteps extends ParkingLotsServiceApplicationTests {

    private int n1;
    private int n2;
    boolean test;

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free
    -------------------------------------------------------------*/
    @Given("Parks space is between {int} and {int}")
    public void free_spaces(int int1, int int2) {

        this.n1 = int1;
        this.n2 = int2;

    }

    @When("I ask whether there are free parks")
    public void how_many_spaces() {

        int freePark = 52;

        if (freePark <= n1 && freePark >= n2) {
            System.out.println("Park space is -> " + freePark);
            test = true;
        } else {
            System.out.println("Park space is -> " + freePark);
            test = false;
        }

    }

    @Then("I should be told that {string}")
    public void answer_is(String string) {
        if (test == true) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed!");
        }
    }

    //asserEqual (string, message)
}
