package esp52.ParkingLotsService;

import esp52.ParkingLotsService.models.ParkingLotation;
import esp52.ParkingLotsService.service.ParksService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

class FreeSpace {

    static String FreeSpace(int today) {
        if (today <= 349 && today > 50) {
            return "is totally free! ";
        } else if (today <= 50 && today > 0) {
            return "has a reduced number of free parking spaces!";
        } else {
            return "has the parking spaces full!";
        }
    }

}

public class ParkingSteps extends ParkingLotsServiceApplicationTests {

    ParksService pS = new ParksService();
    ParkingLotation p1 = pS.getTestPark(349, 7);
    ParkingLotation p2 = pS.getTestPark(25, 3);
    ParkingLotation p3 = pS.getTestPark(0, 0);
    public int idk = p1.getFree();
    public int idk = p2.getFree();
    public int idk = p3.getFree();

    private int n1;
    private int n2;
    private String actualAnswer;
    boolean test;
    private int int1;

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free
    -------------------------------------------------------------*/
    @Given("Parks space is {int}")
    public void free_spaces(int int1) {
        this.int1 = int1;
    }

    @When("I ask whether there are free parks")
    public void i_ask_whether_it_s_Friday_yet() {
        System.out.println("  Space is -> " + idk);
        actualAnswer = FreeSpace.FreeSpace(int1);
    }

//    @When("I ask whether there are free parks")
//    public void how_many_spaces() {
//
//        int freePark = 52;
//
//        if (freePark == 349) {
//            System.out.println("Park space is -> " + freePark);
//            test = true;
//        } else {
//            System.out.println("Park space is -> " + freePark);
//            test = false;
//        }
//
//    }
    @Then("I should be told that {string}")
    public void answer_is(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);

//        if (test == true) {
//            System.out.println("Test Passed!");
//        } else {
//            System.out.println("Test Failed!");
//        }
    }

}
