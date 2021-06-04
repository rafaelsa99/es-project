package esp52.externaAPI.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import esp52.externaAPI.kafka.KafkaProducer;
import esp52.externaAPI.models.Agency;
import esp52.externaAPI.models.ItemsRoutes;
import esp52.externaAPI.models.ItemsStops;
import esp52.externaAPI.models.ParkingLot;
import esp52.externaAPI.models.Route;
import esp52.externaAPI.models.Stop;

@Service
public class LiveInfoService {

	private static final String TOPIC_VEHICLES = "vehicles";
	private static final String TOPIC_PREDICTIONS = "predictions";
	private static final String TOPIC_PARKINGLOTS = "parking";
	private static final Logger logger = LogManager.getLogger(LiveInfoService.class);
	private RestTemplate restTemplate = new RestTemplate();
	private static final String  BASE_URL = "https://api.metro.net/agencies/";
	private List<Agency> agencies = null;
	private List<ParkingLot> parkingLots = null;
	private HashMap<String, ItemsRoutes> routes = new HashMap<>();
	private HashMap<String, ItemsStops> stops = new HashMap<>();
	@Autowired KafkaProducer kafkaProducer;
	
	public LiveInfoService() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			agencies = Arrays.asList(mapper.readValue(getAgencies(), Agency[].class));
			parkingLots = Arrays.asList(mapper.readValue(getParkingLots("lametro"), ParkingLot[].class));
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		for(Agency agency:agencies) {
			String r = getRoutes(agency.getId());
			ItemsRoutes iR = new Gson().fromJson(r, ItemsRoutes.class);
			routes.put(agency.getId(), iR);
			for(Route route: iR.getItems()) {
				String s = getRouteStops(agency.getId(), route.getId());
				ItemsStops iS = new Gson().fromJson(s, ItemsStops.class);
				stops.put(route.getId(), iS);
			}
		}
	}

	@Scheduled(fixedRate = 1000)
	public void updateVehicles() {
		for(Agency agency:agencies) {
			String url = BASE_URL + agency.getDisplay_name() + "/vehicles/";
			ResponseEntity<String> vehicles = restTemplate.getForEntity(url, String.class);
		    if(vehicles.getStatusCode() == HttpStatus.OK) {
				logger.info("Updating Vehicles Information from Agency " + agency.getDisplay_name());
			    kafkaProducer.sendMessage(TOPIC_VEHICLES, vehicles.getBody());
		    }
		}
	}
	
	@Scheduled(fixedRate = 1000)
	public void updatePredictions() {
		for(Entry<String, ItemsStops> e : stops.entrySet()) {
			for(Stop stop: e.getValue().getItems()) {
				String url = BASE_URL + "lametro/routes/" + e.getKey() + "/stops/" + stop.getId() + "/predictions/";
				ResponseEntity<String> predictions = restTemplate.getForEntity(url, String.class);
			    if(predictions.getStatusCode() == HttpStatus.OK) {
					logger.info("Updating Predictions Information from Stop " + stop.getDisplay_name());
					kafkaProducer.sendMessage(TOPIC_PREDICTIONS, predictions.getBody());
			    }
			}
		}
	}
	
	@Scheduled(fixedRate = 1000)
	public void updateParkingLotations() {
		for(ParkingLot park: parkingLots) {
			String url = BASE_URL + "lametro/parking/" + park.getId() + "/";
			ResponseEntity<String> lotation = restTemplate.getForEntity(url, String.class);
			String reply = lotation.getBody().substring(1, lotation.getBody().length() - 2);
			reply = reply.replace("\\", "");
		    if(lotation.getStatusCode() == HttpStatus.OK) {
				logger.info("Updating Parking Lotations from Parking Lot " + park.getName());
				kafkaProducer.sendMessage(TOPIC_PARKINGLOTS, reply);
		    }
		}
	}
	
	public String getParkingLots(String agency){
		String response = restTemplate.getForObject(BASE_URL + agency + "/parking/", String.class);
		response = response.substring(1, response.length() - 2);
		response = response.replace("\\", "");
		return response;
	}
	
	public String getRoutes(String agency){
		String response = restTemplate.getForObject(BASE_URL + agency + "/routes/", String.class);
		return response;
	}
	
	public String getRouteStops(String agency, String route_id){
		String response = restTemplate.getForObject(BASE_URL + agency + "/routes/" + route_id + "/stops/", String.class);
		return response;
	}
	
	public String getAgencies(){
		String response = restTemplate.getForObject(BASE_URL, String.class);
		return response;
	}
}
