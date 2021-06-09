package esp52.ParkingLotsService;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;

//@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@ContextConfiguration
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class ParkingLotsServiceApplicationTests {

//    @Autowired
//    private KafkaConsumer KafkaConsumer;
//
//    @Autowired
//    private KafkaProducer2 kafkaProducer2;
//
//    //private static final String TOPIC_EVENTS = "events";
//    //private static final String TOPIC = "parking";
//    @Value("${test.topic}")
//    private String topic;
//
//    @Autowired
//    ParksService pS = new ParksService();
//    ParkingLotation p1 = pS.getTestPark(349, 7);
//
//    String message = new Gson().toJson(p1);
//
//    //private String test_message = "Park " + p1.getName() + " is totally free!";
//    String msg1 = "Park " + p1.getName() + " is totally free!";
//    String msg2 = "Park " + p1.getName() + " for disabled is totally free!";
//
//    @Test
//    public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
//            throws Exception {
//        kafkaProducer2.sendMessage(topic, message); // p1.getName() = testPark1
//
//        KafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//        assertThat(KafkaConsumer.getLatch().getCount(), equalTo(0L));
//        //assertThat(KafkaConsumer.getMessage(), containsString(msg1));
//    }
//    @Autowired
//    private MockMvc mockMvc;
//
    // Does nothing
    @Test
    void contextLoads() {
    }
//    @Test
//    public void getReact() throws Exception {
//        System.out.println("Request Locations");
//        this.mockMvc.perform(get("http://localhost:8083/lotations/")).andDo(print()).andExpect(status().isOk());
//    }
}
