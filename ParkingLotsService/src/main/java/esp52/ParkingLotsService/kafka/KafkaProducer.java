package esp52.ParkingLotsService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class KafkaProducer {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    //private static final Logger logger = LogManager.getLogger(KafkaProducer.class);

    public void sendMessage(String topic, String message) {
        this.logger.info(message);
        kafkaTemplate.send(topic, message);
    }

}
