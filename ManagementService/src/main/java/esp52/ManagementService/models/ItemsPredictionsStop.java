package esp52.ManagementService.models;

import java.util.Collection;

public class ItemsPredictionsStop {
	
	private String agency_id;
	private String stop_id;
	private Collection<PredictionStop> items;

	public ItemsPredictionsStop() {
	}
	
	public ItemsPredictionsStop(String agency_id, String stop_id, Collection<PredictionStop> items) {
		this.agency_id = agency_id;
		this.items = items;
		this.stop_id = stop_id;
	}
	
	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public String getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	public Collection<PredictionStop> getItems() {
		return items;
	}

	public void setItems(Collection<PredictionStop> items) {
		this.items = items;
	}

}
