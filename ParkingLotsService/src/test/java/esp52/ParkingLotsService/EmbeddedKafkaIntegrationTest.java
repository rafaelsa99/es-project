package esp52.ParkingLotsService;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class EmbeddedKafkaIntegrationTest {

//    @Autowired
//    private KafkaConsumer consumer;
//
//    @Autowired
//    private KafkaProducer producer;
//
//    @Value("${test.topic}")
//    private String topic;
//
//    @Test
//    public void givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived()
//            throws Exception {
//        producer.sendMessage(topic, "Sending with own simple KafkaProducer");
//        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//        assertThat(consumer.getLatch().getCount(), equalTo(0L));
//        assertThat(consumer.getMessage(), containsString("embedded-test-topic"));
//    }
}
