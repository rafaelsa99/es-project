package esp52.AlarmsService.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


@RestController
@RequestMapping("/")
public class AlarmsController {
	
	private static final Logger logger = LogManager.getLogger(AlarmsController.class);
	private List<String> events = new ArrayList<>();
	private String lastEvent;
	
	@GetMapping("/events/")
	public String getEvents(){
		logger.info("Request for events");
		return new Gson().toJson(events);
	}	

	@GetMapping("/events/last/")
	public String getLastEvents(){
		logger.info("Request for the last event");
		List<String> last = new ArrayList<>();
		if(lastEvent.length() > 0)
			last.add(lastEvent);
		lastEvent = "";
		return new Gson().toJson(last);
	}
	
	@KafkaListener(topics = "events", groupId = "lametro")
	public void listenEvents(String message) {
	    events.add(message);
	    lastEvent = message;
	    logger.info("Event: " + message);
	}
}
