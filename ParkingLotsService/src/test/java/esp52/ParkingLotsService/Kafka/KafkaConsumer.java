/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esp52.ParkingLotsService.Kafka;

import esp52.ParkingLotsService.kafka.*;
import java.util.concurrent.CountDownLatch;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LogManager.getLogger(KafkaConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //@KafkaListener(topics = "parking")
    //@KafkaListener(topics = "${test.topic}")
    @KafkaListener(topics = "events")
    public void receive(String consumerRecord) {
        //logger.info("received payload='{}'", consumerRecord.toString());
        //System.out.println("consumerRecord -> " + consumerRecord.toString());
        setMessage(consumerRecord.toString());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
