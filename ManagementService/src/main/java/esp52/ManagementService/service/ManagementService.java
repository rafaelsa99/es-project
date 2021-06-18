package esp52.ManagementService.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import esp52.ManagementService.models.Agency;
import esp52.ManagementService.models.AgencyInfo;
import esp52.ManagementService.models.History;
import esp52.ManagementService.models.ItemsPredictionsStop;
import esp52.ManagementService.models.ItemsRoutes;
import esp52.ManagementService.models.ItemsStops;
import esp52.ManagementService.models.ItemsVehiclesAgency;
import esp52.ManagementService.models.ParkingLotation;
import esp52.ManagementService.models.ParkingLotationFree;
import esp52.ManagementService.models.PredictionInfo;
import esp52.ManagementService.models.PredictionStop;
import esp52.ManagementService.models.Route;
import esp52.ManagementService.models.Stop;
import esp52.ManagementService.models.StopInfo;
import esp52.ManagementService.models.Vehicle;
import esp52.ManagementService.models.VehicleInfo;
import esp52.ManagementService.models.VehiclesRoute;

public class ManagementService {

	private final RestTemplate restTemplate = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper();
	/*private static final String URL_VEHICLES = "http://172.0.0.11:8086/";
	private static final String URL_PARKING = "http://172.0.0.21:8083/";
	private static final String URL_EVENTS = "http://172.0.0.22:8084/";
	private static final String URL_INFO = "http://172.0.0.10:8085/";*/
	private static final String URL_VEHICLES = "http://192.168.160.48:52038/";
	private static final String URL_PARKING = "http://192.168.160.48:52043/";
	private static final String URL_EVENTS = "http://192.168.160.48:52040/";
	private static final String URL_INFO = "http://192.168.160.48:52044/";
	
	private List<Agency> agencies = null;
	private HashMap<String, ItemsRoutes> routes = new HashMap<>();
	private HashMap<String, ItemsStops> stops = new HashMap<>();
	
	public String getParkingInfo() {
		List<ParkingLotation> parks = new ArrayList<>();
		try {
			String response = restTemplate.getForObject(URL_PARKING + "lotations/", String.class);
			parks = Arrays.asList(mapper.readValue(response, ParkingLotation[].class));
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		return new Gson().toJson(parks);
	}

	public String getAllAgenciesInfo() {
		if(agencies == null)
			fillAgencies();
		if(routes.isEmpty())
			fillRoutes();
		if(stops.isEmpty())
			fillRoutesStops();
		List<AgencyInfo> infos = new ArrayList<>();
		for(Agency agenc:agencies) {
			String agency = agenc.getId();
			List<ItemsPredictionsStop> predictions = new ArrayList<>();
			ItemsVehiclesAgency vehicles = null;
			try {
				String response = restTemplate.getForObject(URL_VEHICLES + agency + "/predictions/", String.class);
				predictions = Arrays.asList(mapper.readValue(response, ItemsPredictionsStop[].class));
				response = restTemplate.getForObject(URL_VEHICLES + agency + "/vehicles/", String.class);
				vehicles = new Gson().fromJson(response, ItemsVehiclesAgency.class);
			} catch (JsonProcessingException e) {
				System.out.println(e.toString());
			}
			HashMap<String, StopInfo> stopsInfo = new HashMap<>();
			for(Route route: routes.get(agency).getItems()) {
				for(Stop itemstop: stops.get(route.getId()).getItems()) {
					List<PredictionInfo> pi = new ArrayList<>();
					for(ItemsPredictionsStop p: predictions) {
						for(PredictionStop s: p.getItems()) {
							if(s.getStop_id().equals(itemstop.getId())) {
								PredictionInfo info = new PredictionInfo(route.getDisplay_name(), s.getMinutes(), s.getSeconds());
								pi.add(info);
							}
						}
					}
					StopInfo sinfo = new StopInfo(itemstop.getDisplay_name(), itemstop.getLatitude(), itemstop.getLongitude(), pi);
					stopsInfo.put(itemstop.getId(), sinfo);
				}
			}
			List<VehicleInfo> vehiclesInfo = new ArrayList<>();
			for(Vehicle v: vehicles.getItems()) {
				vehiclesInfo.add(new VehicleInfo(v.getId(), getRouteName(agency, v.getRoute_id()), v.getLatitude(), v.getLongitude(), v.getSeconds_since_report(), v.getHeading()));
			}
			infos.add(new AgencyInfo(vehiclesInfo, stopsInfo.values()));
		}
		return new Gson().toJson(infos);
	}

	public String getAgencyInfo(String agency) {
		if(agencies == null)
			fillAgencies();
		if(routes.isEmpty())
			fillRoutes();
		if(stops.isEmpty())
			fillRoutesStops();
		List<AgencyInfo> infos = new ArrayList<>();
		List<ItemsPredictionsStop> predictions = new ArrayList<>();
		ItemsVehiclesAgency vehicles = null;
		try {
			String response = restTemplate.getForObject(URL_VEHICLES + agency + "/predictions/", String.class);
			predictions = Arrays.asList(mapper.readValue(response, ItemsPredictionsStop[].class));
			response = restTemplate.getForObject(URL_VEHICLES + agency + "/vehicles/", String.class);
			vehicles  = new Gson().fromJson(response, ItemsVehiclesAgency.class);
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		HashMap<String, StopInfo> stopsInfo = new HashMap<>();
		for(Route route: routes.get(agency).getItems()) {
			for(Stop itemstop: stops.get(route.getId()).getItems()) {
				List<PredictionInfo> pi = new ArrayList<>();
				for(ItemsPredictionsStop p: predictions) {
					for(PredictionStop s: p.getItems()) {
						if(s.getStop_id().equals(itemstop.getId())) {
							PredictionInfo info = new PredictionInfo(route.getDisplay_name(), s.getMinutes(), s.getSeconds());
							pi.add(info);
						}
					}
				}
				StopInfo sinfo = new StopInfo(itemstop.getDisplay_name(), itemstop.getLatitude(), itemstop.getLongitude(), pi);
				stopsInfo.put(itemstop.getId(), sinfo);
			}
		}
		List<VehicleInfo> vehiclesInfo = new ArrayList<>();
		for(Vehicle v: vehicles.getItems()) {
			vehiclesInfo.add(new VehicleInfo(v.getId(), getRouteName(agency, v.getRoute_id()), v.getLatitude(), v.getLongitude(), v.getSeconds_since_report(), v.getHeading()));
		}
		infos.add(new AgencyInfo(vehiclesInfo, stopsInfo.values()));
		return new Gson().toJson(infos);
	}
	
	private void fillAgencies() {
		try {
			String response = restTemplate.getForObject(URL_INFO + "agencies/", String.class);
			agencies = Arrays.asList(mapper.readValue(response, Agency[].class));
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
	}
	
	private void fillRoutes() {
		for(Agency agency:agencies) {
			String response = restTemplate.getForObject(URL_INFO + agency.getId() + "/routes/", String.class);
			ItemsRoutes route = new Gson().fromJson(response, ItemsRoutes.class);
			routes.put(agency.getId(), route);
		}
	}
	
	private void fillRoutesStops() {
		for(Map.Entry<String, ItemsRoutes> e: routes.entrySet()) {
			for(Route route:e.getValue().getItems()) {
				String response = restTemplate.getForObject(URL_INFO + e.getKey() + "/routes/" + route.getId() + "/stops/", String.class);
				ItemsStops stop = new Gson().fromJson(response, ItemsStops.class);
				stops.put(route.getId(), stop);
			}
		}
	}

	private String getRouteName(String agency, String route_id) {
		for(Route ir: routes.get(agency).getItems()) {
			if(ir.getId().equals(route_id))
				return ir.getDisplay_name();
		}
		return "";
	}

	public String getEvents(boolean lastEvent) {
		String url;
		if(lastEvent)
			url = URL_EVENTS + "events/last/";
		else
			url = URL_EVENTS + "events/";
		List<String> events = new ArrayList<>();
		try {
			String response = restTemplate.getForObject(url, String.class);
			events = Arrays.asList(mapper.readValue(response, String[].class));
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		return new Gson().toJson(events);
	}

	public String getHistory() {
		if(agencies == null)
			fillAgencies();
		if(routes.isEmpty())
			fillRoutes();
		if(stops.isEmpty())
			fillRoutesStops();
		History history = new History();
		String urlVehicles = URL_VEHICLES + "history/";
		String urlParks = URL_PARKING + "history/";
		List<VehiclesRoute> vR = new ArrayList<>();
		List<ParkingLotationFree> pF = new ArrayList<>();
		HashMap<String, Integer> sums = new HashMap<>();
		HashMap<String, Integer> counters = new HashMap<>();
		try {
			String response = restTemplate.getForObject(urlVehicles, String.class);
			vR = Arrays.asList(mapper.readValue(response, VehiclesRoute[].class));
			response = restTemplate.getForObject(urlParks, String.class);
			pF =  Arrays.asList(mapper.readValue(response, ParkingLotationFree[].class));
		} catch (JsonProcessingException e) {
			System.out.println(e.toString());
		}
		for(VehiclesRoute v: vR) {
			if(sums.containsKey(v.getRoute_id())) {
				 sums.replace(v.getRoute_id(), sums.get(v.getRoute_id()) + v.getNum_vehicles());
				 counters.replace(v.getRoute_id(), counters.get(v.getRoute_id()) + 1);
			} else {
				sums.put(v.getRoute_id(), v.getNum_vehicles());
				counters.put(v.getRoute_id(), 1);
			}
		}
		List<Integer> avgsVehicles = new ArrayList<>();
		List<String> namesRoutes = new ArrayList<>();
		List<Integer> avgsParks = new ArrayList<>();
		List<String> namesParks = new ArrayList<>();
		for(Map.Entry<String, Integer> s: sums.entrySet()) {
			namesRoutes.add(getRouteName("lametro-rail", s.getKey()));
			Integer roundedAvg = s.getValue() / counters.get(s.getKey());
			avgsVehicles.add(roundedAvg);
		}
		history.setAvg_vehicles(avgsVehicles);
		history.setRoutes_names(namesRoutes);
		sums.clear();
		counters.clear();
		for(ParkingLotationFree p: pF) {
			if(sums.containsKey(p.getName())) {
				 sums.replace(p.getName(), sums.get(p.getName()) + p.getFree());
				 counters.replace(p.getName(), counters.get(p.getName()) + 1);
			} else {
				sums.put(p.getName(), p.getFree());
				counters.put(p.getName(), 1);
			}
		}
		for(Map.Entry<String, Integer> s: sums.entrySet()) {
			Integer roundedAvg = sums.get(s.getKey()) / counters.get(s.getKey());
			avgsParks.add(roundedAvg);
			namesParks.add(s.getKey());
		}
		history.setAvg_parks(avgsParks);
		history.setParks_names(namesParks);
		return new Gson().toJson(history);
	}

}
