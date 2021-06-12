package esp52.ManagementService.models;

import java.util.Collection;

public class History {

	private Collection<String> routes_names;
	private Collection<Integer> avg_vehicles;
	private Collection<String> parks_names;
	private Collection<Integer> avg_parks;
	
	public Collection<String> getRoutes_names() {
		return routes_names;
	}
	public Collection<String> getParks_names() {
		return parks_names;
	}
	public void setParks_names(Collection<String> parks_names) {
		this.parks_names = parks_names;
	}
	public Collection<Integer> getAvg_parks() {
		return avg_parks;
	}
	public void setAvg_parks(Collection<Integer> avg_parks) {
		this.avg_parks = avg_parks;
	}
	public void setRoutes_names(Collection<String> routes_names) {
		this.routes_names = routes_names;
	}
	public Collection<Integer> getAvg_vehicles() {
		return avg_vehicles;
	}
	public void setAvg_vehicles(Collection<Integer> avg_vehicles) {
		this.avg_vehicles = avg_vehicles;
	}
	
	
}
