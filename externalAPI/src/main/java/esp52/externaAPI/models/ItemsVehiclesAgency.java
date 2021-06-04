package esp52.externaAPI.models;

import java.util.Collection;

public class ItemsVehiclesAgency {
	
	private String agency_id;
	private Collection<Vehicle> items;
	
	public ItemsVehiclesAgency() {}
	
	public ItemsVehiclesAgency(String agency_id, Collection<Vehicle> items) {
		this.agency_id = agency_id;
		this.items = items;
	}

	public String getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	public Collection<Vehicle> getItems() {
		return items;
	}

	public void setItems(Collection<Vehicle> items) {
		this.items = items;
	}
}
