package esp52.ParkingLotsService;

import esp52.ParkingLotsService.Repository.ParkingLotationRepository;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { ParkingLotsServiceApplication.class, H2TestProfileJPAConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@ContextConfiguration
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class ParkingLotsServiceApplicationTests {

//    @Autowired
//    private MockMvc mockMvc;
    // Does nothing 
    @Test
    void contextLoads() {
    }
//
//    @Test
//    public void getReact() throws Exception {
//        System.out.println("Request Locations");
//        this.mockMvc.perform(get("http://localhost:8083/lotations/")).andDo(print()).andExpect(status().isOk());
//    }
}
