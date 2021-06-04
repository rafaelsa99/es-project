package esp52.ManagementService.models;

public class PredictionInfo {
	private String route_name; 
	private int minutes; 
	private int seconds;
	
	public PredictionInfo() {}
	
	public PredictionInfo(String route_name, int minutes, int seconds) {
		this.route_name = route_name;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	} 
	
	
}
