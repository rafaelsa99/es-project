package esp52.ParkingLotsService;

import com.google.gson.Gson;
import esp52.ParkingLotsService.Kafka.KafkaConsumer;
import esp52.ParkingLotsService.Kafka.KafkaProducer2;
import esp52.ParkingLotsService.models.ParkingLotation;
import esp52.ParkingLotsService.service.ParksService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;

//class FreeSpace {
//
//    static String FreeSpace(int today) {
//        if (today == 349) {
//            return "is totally free!";
//        } else if (today <= 50 && today > 0) {
//            return "has a reduced number of free parking spaces!";
//        } else {
//            return "has the parking spaces full!";
//        }
//    }
//
//    static String FreeDisabledSpace(int today2) {
//        if (today2 == 7) {
//            return "for disabled is totally free!";
//        } else if (today2 <= 3 && today2 > 0) {
//            return "has a reduced number of free parking spaces for disabled!";
//        } else {
//            return "has the disabled parking spaces full!";
//        }
//    }
//
//}
public class ParkingSteps extends ParkingLotsServiceApplicationTests {

    //@Autowired
    ParksService pS = new ParksService();

    @Autowired
    private KafkaConsumer KafkaConsumer;

    @Autowired
    private KafkaProducer2 kafkaProducer2;

    @Value("${test.topic}")
    private String topic;

    ParkingLotation p1 = new ParkingLotation();

    private String actualAnswer;

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free
    -------------------------------------------------------------*/
    @Given("Parks space is 349")
    public void free_spaces_1() {
        p1 = pS.getTestPark(349, 7);

    }

//    @Given("Parks space is between 50 and 1")
//    public void free_spaces_2() {
//    }
//
//    @Given("Parks space is 0")
//    public void free_spaces_3() {
//    }
    @When("I ask whether there are free parks")
    public void fp1() {
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
//        System.out.println("  Park space is -> " + idk1);
//        actualAnswer = FreeSpace.FreeSpace(idk1);
    }

//    @When("I ask whether there are free parkss")
//    public void fp2() {
//        System.out.println("  Park space is -> " + idk2);
//        actualAnswer = FreeSpace.FreeSpace(idk2);
//    }
//
//    @When("I ask whether there are free parksss")
//    public void fp3() {
//        System.out.println("  Park space is -> " + idk3);
//        actualAnswer = FreeSpace.FreeSpace(idk3);
//    }
    @Then("I should be told that {string}")
    public void answer_is(String expectedAnswer) throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
        //System.out.println("actualAnswer ->" + actualAnswer);
        assertEquals(expectedAnswer, actualAnswer);

    }

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free (Disabled People)
    -------------------------------------------------------------*/
//    @Given("Disabled parks space is 7")
//    public void free_spaces_1d() {
//    }
//
//    @Given("Disabled parks space is between 3 and 1")
//    public void free_spaces_2d() {
//    }
//
//    @Given("Disabled parks space is 0")
//    public void free_spaces_3d() {
//    }
//
//    @When("I ask whether there are free disabled parks")
//    public void fp1d() {
//        System.out.println("  Disabled park space is -> " + idk1d);
//        actualAnswer = FreeSpace.FreeDisabledSpace(idk1d);
//    }
//
//    @When("I ask whether there are free disabled parkss")
//    public void fp2d() {
//        System.out.println("  Disabled park space is -> " + idk2d);
//        actualAnswer = FreeSpace.FreeDisabledSpace(idk2d);
//    }
//
//    @When("I ask whether there are free disabled parksss")
//    public void fp3d() {
//        System.out.println("  Disabled park space is -> " + idk3d);
//        actualAnswer = FreeSpace.FreeDisabledSpace(idk3d);
//    }
//
//    @Then("I should be told {string}")
//    public void answer_isd(String expectedAnswer) {
//        assertEquals(expectedAnswer, actualAnswer);
//
//    }
}
