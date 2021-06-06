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
            return "is totally free!";
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

    public int idk1 = p1.getFree();
    public int idk2 = p2.getFree();
    public int idk3 = p3.getFree();
    public int idk1d = p1.getDisabledfree();
    public int idk2d = p2.getDisabledfree();
    public int idk3d = p3.getDisabledfree();

    private String actualAnswer;

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free
    -------------------------------------------------------------*/
    @Given("Parks space is 349")
    public void free_spaces_1() {
    }

    @Given("Parks space is between 50 and 1")
    public void free_spaces_2() {
    }

    @Given("Parks space is 0")
    public void free_spaces_3() {
    }

    @When("I ask whether there are free parks")
    public void fp1() {
        System.out.println("  Space is -> " + idk1);
        actualAnswer = FreeSpace.FreeSpace(idk1);
    }

    @When("I ask whether there are free parkss")
    public void fp2() {
        System.out.println("  Space is -> " + idk2);
        actualAnswer = FreeSpace.FreeSpace(idk2);
    }

    @When("I ask whether there are free parksss")
    public void fp3() {
        System.out.println("  Space is -> " + idk3);
        actualAnswer = FreeSpace.FreeSpace(idk3);
    }

    @Then("I should be told that {string}")
    public void answer_is(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);

    }

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free (Disabled People)
    -------------------------------------------------------------*/
    @Given("Disabled Parks space is 7")
    public void free_spaces_1d() {
    }

    @Given("Disabled Parks space is between 3 and 1")
    public void free_spaces_2d() {
    }

    @Given("Disabled Parks space is 0")
    public void free_spaces_3d() {
    }

    @When("I ask whether there are free disabled parks")
    public void fp1d() {
        System.out.println("  Space is -> " + idk1d);
        actualAnswer = FreeSpace.FreeSpace(idk1d);
    }

    @When("I ask whether there are free disabled parkss")
    public void fp2d() {
        System.out.println("  Space is -> " + idk2d);
        actualAnswer = FreeSpace.FreeSpace(idk2d);
    }

    @When("I ask whether there are free disabled parksss")
    public void fp3d() {
        System.out.println("  Space is -> " + idk3d);
        actualAnswer = FreeSpace.FreeSpace(idk3d);
    }

    @Then("I should be told that {string}")
    public void answer_isd(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);

    }
}
