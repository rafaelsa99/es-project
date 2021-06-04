package esp52.ManagementService.models;

import java.util.Collection;

public class AgencyInfo {
	private Collection<VehicleInfo> vehicles;
	private Collection<StopInfo> stops;
	
	public AgencyInfo() {}
	
	public AgencyInfo(Collection<VehicleInfo> vehicles, Collection<StopInfo> stops) {
		super();
		this.vehicles = vehicles;
		this.stops = stops;
	}
	public Collection<VehicleInfo> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<VehicleInfo> vehicles) {
		this.vehicles = vehicles;
	}
	public Collection<StopInfo> getStops() {
		return stops;
	}
	public void setStops(Collection<StopInfo> stops) {
		this.stops = stops;
	}
	
	
}
