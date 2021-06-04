package com.es.kafkaConsumer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
    @KafkaListener(topics="my_topic", groupId="test-consumer-group")
    public void getMessage(String message){

        System.out.println(message);

    }
}