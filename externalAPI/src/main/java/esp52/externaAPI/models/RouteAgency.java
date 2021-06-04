package esp52.externaAPI.models;

public class RouteAgency {
	private String route_id;
	private String agency_id;
	
	public RouteAgency() {}
	
	public RouteAgency(String route_id, String agency_id) {
		this.route_id = route_id;
		this.agency_id = agency_id;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getAgency_id() {
		return agency_id;
	}
	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	} 

	
}
