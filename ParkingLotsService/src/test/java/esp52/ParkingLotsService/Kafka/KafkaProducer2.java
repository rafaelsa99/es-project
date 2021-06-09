package esp52.ParkingLotsService.Kafka;

import esp52.ParkingLotsService.kafka.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaProducer2 {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger logger = LogManager.getLogger(KafkaProducer2.class);

    public void sendMessage(String topic, String message) {
        logger.info(message);
        System.out.println("sending payload");
        kafkaTemplate.send(topic, message);
    }

}
