package com.es.projectbackend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.es.projectbackend.kafka.KafkaProducer;
import com.es.projectbackend.model.Plane;
import com.es.projectbackend.model.States;
import com.es.projectbackend.repository.PlaneRepository;

@Service
public class OpenSkyService {
	@Autowired
	private PlaneRepository planeRepository;

	@Autowired KafkaProducer kafkaProducer;
	
	private Collection<Plane> planes;
	private RestTemplate restTemplate;
	
	private static final Double LAT_MIN = 33.741204;
	private static final Double LAT_MAX = 34.171230;
	private static final Double LON_MIN = -118.716612;
	private static final Double LON_MAX = -118.099742;
	
	private static final Logger logger = LogManager.getLogger(OpenSkyService.class);
	
	public OpenSkyService() {
		restTemplate = new RestTemplate();
	}

	@Scheduled(fixedRate = 5000)
    public void updateData(){
		String url = "https://opensky-network.org/api/states/all?lamin=" + LAT_MIN + "&lomin=" + LON_MIN + "&lamax=" + LAT_MAX + "&lomax=" + LON_MAX;
		logger.info("Calling External API");
		ResponseEntity<States> openSkyStates = restTemplate.getForEntity(url, States.class);
	    if(openSkyStates.getStatusCode() == HttpStatus.OK) {
		    logger.info("Success on calling the API");
	    	planes = openSkyStates.getBody().getStates();
			logger.info("Updating information on " + planes.size() + " planes");
			int countHistoryBeforeUpdate = getHistorySize();
		    planeRepository.saveAll(openSkyStates.getBody().getStatesWithAltitude());
		    int newPlanes = getHistorySize() - countHistoryBeforeUpdate;
		    logger.info("Updating the database: " + newPlanes + " new planes entering the region");
			if(newPlanes > 0)
		    	kafkaProducer.sendMessage("openskyevents", newPlanes + " new planes entering the region");
	    }
	    else
	    	logger.error("Error while calling the API: " + openSkyStates.getStatusCode().toString());
    }
	
	public List<Plane> getAllPlanes(){
		if(planes != null) {
			logger.info("Returning " + planes.size() + " planes for live information");
			return new ArrayList<>(planes);
		}
		logger.info("No live plane information");
		return new ArrayList<>();
	}

	public List<Plane> getHistory() {
		List<Plane> history = planeRepository.findAll();
		logger.info("Returning the stored information of " + history.size() + " planes from the database");
		return history;
	}

	private int getHistorySize() {
		return planeRepository.findAll().size();
	}
}
