package esp52.ParkingLotsService.Kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KafkaProducer2 {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    //private static final Logger logger = LogManager.getLogger(KafkaProducer2.class);

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        this.logger.info("Sending Message (LogTest)");
    }

}
