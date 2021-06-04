package esp52.VehiclesService.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import esp52.VehiclesService.models.ItemsPredictionsStop;
import esp52.VehiclesService.models.ItemsVehiclesAgency;
import esp52.VehiclesService.models.StopAgency;


@Component
public class VehiclesService {

	private static final Logger logger = LogManager.getLogger(VehiclesService.class);
	private HashMap<String, ItemsVehiclesAgency> vehiclesInfo = new HashMap<>();
	private HashMap<StopAgency, ItemsPredictionsStop> predictionsInfo = new HashMap<>();
	
	@KafkaListener(topics = "vehicles", groupId = "lametro")
	public void vehicleUpdate(String message) {
		ItemsVehiclesAgency iV = new Gson().fromJson(message, ItemsVehiclesAgency.class);
		if(vehiclesInfo.containsKey(iV.getAgency_id()))
			vehiclesInfo.replace(iV.getAgency_id(), iV);
		else
			vehiclesInfo.put(iV.getAgency_id(), iV);
		logger.info("Updating Vehicles Live Information from Agency " + iV.getAgency_id());
		
	}
	
	@KafkaListener(topics = "predictions", groupId = "lametro")
	public void predictionUpdate(String message) {
		ItemsPredictionsStop iP = new Gson().fromJson(message, ItemsPredictionsStop.class);
		StopAgency sa = new StopAgency(iP.getStop_id(), iP.getAgency_id());
		if(predictionsInfo.containsKey(sa))
			predictionsInfo.replace(sa, iP);
		else
			predictionsInfo.put(sa, iP);
		logger.info("Updating predictions information from stop " + sa.getStop_id() + " of the agency " + sa.getAgency_id());
	}

	public String getVehicles(String agency) {
		String vehiclesJson =  new Gson().toJson(vehiclesInfo.get(agency));
		return vehiclesJson;
	}

	public String getPredictions(String agency) {
		List<ItemsPredictionsStop> listPredictions = new ArrayList<>();
		for(Entry<StopAgency, ItemsPredictionsStop> e : predictionsInfo.entrySet())
			if(e.getKey().getAgency_id().equals(agency))
				listPredictions.add(e.getValue());
		String predictionsJson =  new Gson().toJson(listPredictions);
		return predictionsJson;
	}
}
