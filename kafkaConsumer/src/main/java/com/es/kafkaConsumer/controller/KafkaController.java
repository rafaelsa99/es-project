package com.es.kafkaConsumer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class KafkaController {
	
	List<String> events = new ArrayList<>();
	List<String> logs = new ArrayList<>();
	
	@GetMapping("/events")
	public String getEvents(){
		return new Gson().toJson(events);
	}	
	
	@GetMapping("/logs")
	public String getLogs(){
		return new Gson().toJson(logs);
	}	
	
	@KafkaListener(topics = "openskyevents", groupId = "opensky_group")
	public void listenEvents(String message) {
	    events.add(message);
	}
	
	@KafkaListener(topics = "opensky", groupId = "opensky_group")
	public void listenLogs(String message) {
	    logs.add(message);
	}
}
