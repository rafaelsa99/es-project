package esp52.StaticInfo.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import esp52.StaticInfo.models.Agency;
import esp52.StaticInfo.models.ItemsRoutes;
import esp52.StaticInfo.models.ItemsStops;
import esp52.StaticInfo.models.ParkingLot;

public class StaticInfoData {
	private List<Agency> agencies = null;
	private List<ParkingLot> parkingLots = null;
	private HashMap<String, ItemsRoutes> routes = new HashMap<>();
	private HashMap<String, ItemsStops> stops = new HashMap<>();
	private RestTemplate restTemplate = new RestTemplate();
	private static final String  BASE_URL = "http://172.0.0.20:8082/";
	
	public String getAgencies() {
		if(agencies == null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				String response = restTemplate.getForObject(BASE_URL + "agencies/", String.class);
				agencies = Arrays.asList(mapper.readValue(response, Agency[].class));
			} catch (JsonProcessingException e) {
				System.out.println(e.toString());
			}
		}
		return new Gson().toJson(agencies);
	}

	public String getParkingLots() {
		if(parkingLots == null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				String response = restTemplate.getForObject(BASE_URL + "lametro/parkinglots/", String.class);
				parkingLots = Arrays.asList(mapper.readValue(response, ParkingLot[].class));
			} catch (JsonProcessingException e) {
				System.out.println(e.toString());
			}
		}
		return new Gson().toJson(parkingLots);
	}

	public String getRoutes(String agency) {		
		if(!routes.containsKey(agency)) {
			String response = restTemplate.getForObject(BASE_URL + agency + "/routes/", String.class);
			ItemsRoutes iR = new Gson().fromJson(response, ItemsRoutes.class);
			routes.put(agency, iR);
		}
		return new Gson().toJson(routes.get(agency));
	}

	public String getRouteStops(String agency, String route_id) {
		if(!stops.containsKey(route_id)) {
			String response = restTemplate.getForObject(BASE_URL + agency + "/routes/" + route_id + "/stops/", String.class);
			ItemsStops iS = new Gson().fromJson(response, ItemsStops.class);
			stops.put(route_id, iS);
		}
		return new Gson().toJson(stops.get(route_id));
	}

	
}
