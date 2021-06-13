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
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces test1")
    public void free_spaces1(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\" test1")
    public void fp1() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that test1 {string}")
    public void answer_is1(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces test2")
    public void free_spaces2(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\" test2")
    public void fp2() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that test2 {string}")
    public void answer_is2(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces test3")
    public void free_spaces3(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\" test3")
    public void fp3() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that test3 {string}")
    public void answer_is_3(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }


    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free (Disabled People)
    -------------------------------------------------------------*/
    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces test4")
    public void free_spaces4(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\" test4")
    public void fp4() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that test4 {string}")
    public void answer_is4(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces test5")
    public void free_spaces5(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\" test5")
    public void fp5() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that test5 {string}")
    public void answer_is5(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("a parking lot with a total of {int} parking spaces and {int} disabled parking spaces and with {int} free parking spaces and {int} disabled free parking spaces test6")
    public void free_spaces6(int total, int totald, int free, int freed) {
        p1 = pS.getTestPark(total, free, totald, freed);
        String msg = new Gson().toJson(p1);
        kafkaProducer2.sendMessage(topic, msg);
    }

    @When("the event is received through kafka topic \"events\" test6")
    public void fp6() throws InterruptedException {
        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        actualAnswer = KafkaConsumer.getMessage();
    }

    @Then("I should be told that test6 {string}")
    public void answer_is_6(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}
