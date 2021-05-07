package com.es.projectbackend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.es.projectbackend.kafka.KafkaProducer;
import com.es.projectbackend.model.*;
//import com.es.projectbackend.repository.VehicleRepository;

@Service
public class LAMetroService {
	//@Autowired
	//private VehicleRepository planeRepository;

	@Autowired KafkaProducer kafkaProducer;
	
	private Items vehicles;
	private RestTemplate restTemplate;
	
	
	private static final Logger logger = LogManager.getLogger(LAMetroService.class);
	private static final String URL_API = "https://api.metro.net/";
	
	public LAMetroService() {
		restTemplate = new RestTemplate();
		/*List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		//Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters); */
	}

	@Scheduled(fixedRate = 5000)
    public void updateLAMetroVehicles(){
		String url = URL_API + "agencies/lametro/vehicles/";
		logger.info("Calling External API " + url);
		ResponseEntity<Items> lametrovehicles = restTemplate.getForEntity(url, Items.class);
	    if(lametrovehicles.getStatusCode() == HttpStatus.OK) {
		    logger.info("Success on calling the API");
		    vehicles = lametrovehicles.getBody();
			logger.info("Updating information on " + vehicles.getItems().size() + " vehicles");
		    //kafkaProducer.sendMessage("openskyevents", newPlanes + " new planes entering th region");
	    }
	    else
	    	logger.error("Error while calling the API: " + lametrovehicles.getStatusCode().toString());
    }
	
	public List<Vehicle> getAllVehicles(){
		if(vehicles != null) {
			logger.info("Returning " + vehicles.getItems().size() + " vehicles for live information");
			return new ArrayList<>(vehicles.getItems());
		}
		logger.info("No live vehicles information");
		return new ArrayList<>();
	}
}
