package esp52.ManagementService.models;

public class VehicleInfo {
	private String id;
	private String route_name; 
	private Double latitude; 
	private Double longitude;
	private int seconds_since_report;
	private Double heading;
	
	public VehicleInfo() {}
	
	public VehicleInfo(String id, String route_name, Double latitude, Double longitude, int seconds_since_report,
			Double heading) {
		this.id = id;
		this.route_name = route_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.seconds_since_report = seconds_since_report;
		this.heading = heading;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public int getSeconds_since_report() {
		return seconds_since_report;
	}
	public void setSeconds_since_report(int seconds_since_report) {
		this.seconds_since_report = seconds_since_report;
	}
	public Double getHeading() {
		return heading;
	}
	public void setHeading(Double heading) {
		this.heading = heading;
	}
	
	
}
