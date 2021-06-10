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
    //ParkingLotation p2 = new ParkingLotation();

    private String actualAnswer, actualAnswer2;

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free
    -------------------------------------------------------------*/
    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces")
    public void free_spaces1(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\"")
    public void fp1() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that {string}")
    public void answer_is1(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spacess")
    public void free_spaces2(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"eventss\"")
    public void fp2() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told thatt {string}")
    public void answer_is2(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spacesss")
    public void free_spaces3(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"eventsss\"")
    public void fp3() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told thattt {string}")
    public void answer_is_3(String expectedAnswer) {
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
