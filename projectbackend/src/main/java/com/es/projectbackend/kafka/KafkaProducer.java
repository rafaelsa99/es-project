package com.es.projectbackend.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {

	private static final String TOPIC = "openskyevents";
	private static final Logger logger = LogManager.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

	/*public void sendMessage(String message) {
        
	    ListenableFuture<SendResult<String, String>> future = 
	      kafkaTemplate.send(TOPIC, message);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
	        @Override
	        public void onSuccess(SendResult<String, String> result) {
	        	logger.info("Message \" " + message + "\" sent");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	        	logger.error("Unable to send message \"" + message + "\"");
	        }
	    });
	}*/
}
