/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esp52.ParkingLotsService.Kafka;

import java.util.concurrent.CountDownLatch;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //private static final Logger logger = LogManager.getLogger(KafkaConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @KafkaListener(topics = "events")
    public void receive(String consumerRecord) {
        setMessage(consumerRecord.toString());
        this.logger.info("Setting Message");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
