package esp52.ParkingLotsService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@CucumberContextConfiguration
@ContextConfiguration
class ParkingLotsServiceApplicationTests {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void getReact() throws Exception {
//        System.out.println("Request React");
//        this.mockMvc.perform(get("http://localhost:8083/lotations/")).andDo(print()).andExpect(status().isOk());
//    }
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

        int freePark = 2;

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
