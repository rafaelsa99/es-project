package esp52.externaAPI.models;

public class Vehicle {
	private String id;
	private String run_id; 
	private String route_id; 
	private Double latitude; 
	private Double longitude;
	private boolean predictable;
	private int seconds_since_report;
	private Double heading;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRun_id() {
		return run_id;
	}
	public void setRun_id(String run_id) {
		this.run_id = run_id;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
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
	public boolean isPredictable() {
		return predictable;
	}
	public void setPredictable(boolean predictable) {
		this.predictable = predictable;
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
