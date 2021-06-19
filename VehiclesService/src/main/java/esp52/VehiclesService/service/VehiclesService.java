package esp52.VehiclesService.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import esp52.VehiclesService.Repository.VehiclesRoutesRepository;
import esp52.VehiclesService.models.ItemsPredictionsStop;
import esp52.VehiclesService.models.ItemsVehiclesAgency;
import esp52.VehiclesService.models.StopAgency;
import esp52.VehiclesService.models.Vehicle;
import esp52.VehiclesService.models.VehiclesRoute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Component
public class VehiclesService {

	@Autowired VehiclesRoutesRepository vehiclesRoutesRepository;
	
	private static final Logger logger = LogManager.getLogger(VehiclesService.class);
	private HashMap<String, ItemsVehiclesAgency> vehiclesInfo = new HashMap<>();
	private HashMap<String, ItemsPredictionsStop> predictionsInfo = new HashMap<>();
	private HashMap<String, StopAgency> stops = new HashMap<>();
	HashMap<String, VehiclesRoute> counters = new HashMap<>();
	
	@KafkaListener(topics = "vehicles", groupId = "lametro")
	public void vehicleUpdate(String message) {
		ItemsVehiclesAgency iV = new Gson().fromJson(message, ItemsVehiclesAgency.class);
		if(vehiclesInfo.containsKey(iV.getAgency_id()))
			vehiclesInfo.replace(iV.getAgency_id(), iV);
		else
			vehiclesInfo.put(iV.getAgency_id(), iV);
		saveOnHistory(iV);
		logger.info("Updating Vehicles Live Information from Agency " + iV.getAgency_id());
		
	}
	
	@KafkaListener(topics = "predictions", groupId = "lametro")
	public void predictionUpdate(String message) {
		ItemsPredictionsStop iP = new Gson().fromJson(message, ItemsPredictionsStop.class);
		StopAgency sa = new StopAgency(iP.getStop_id(), iP.getAgency_id());
		if(predictionsInfo.containsKey(sa.getStop_id())) {
			predictionsInfo.replace(sa.getStop_id(), iP);
			stops.replace(sa.getStop_id(), sa);
		} else {
			predictionsInfo.put(sa.getStop_id(), iP);
			stops.put(sa.getStop_id(), sa);
		}
		logger.info("Updating predictions information from stop " + sa.getStop_id() + " of the agency " + sa.getAgency_id());
	}

	public String getVehicles(String agency) {
		String vehiclesJson =  new Gson().toJson(vehiclesInfo.get(agency));
		return vehiclesJson;
	}

	public String getPredictions(String agency) {
		List<ItemsPredictionsStop> listPredictions = new ArrayList<>();
		for(Entry<String, ItemsPredictionsStop> e : predictionsInfo.entrySet())
			if(stops.get(e.getKey()).getAgency_id().equals(agency))
				listPredictions.add(e.getValue());
		String predictionsJson =  new Gson().toJson(listPredictions);
		return predictionsJson;
	}
	
	private void saveOnHistory(ItemsVehiclesAgency iV) {
		HashMap<String, VehiclesRoute> newcounters = new HashMap<>();
		for(Vehicle c: iV.getItems()) {
			if(newcounters.containsKey(c.getRoute_id()))
				newcounters.get(c.getRoute_id()).incrementNumVehicles();
			else
				newcounters.put(c.getRoute_id(), new VehiclesRoute(c.getRoute_id()));
		}
		for(VehiclesRoute v: newcounters.values()) {
			if(counters.containsKey(v.getRoute_id())) {
				if(counters.get(v.getRoute_id()).getNum_vehicles() != v.getNum_vehicles()) {
					counters.get(v.getRoute_id()).setNum_vehicles(v.getNum_vehicles());
					vehiclesRoutesRepository.save(v);
				}
			} else {
				counters.put(v.getRoute_id(), v);
				vehiclesRoutesRepository.save(v);
			}
		}
	}
	
	public String getHistory(){
		return new Gson().toJson(vehiclesRoutesRepository.findAll());
	}
}
