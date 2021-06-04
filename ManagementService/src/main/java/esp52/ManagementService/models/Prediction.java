package esp52.ManagementService.models;

public class Prediction {
	private String route_id; 
	private String block_id; 
	private String run_id; 
	private boolean is_departing; 
	private int minutes; 
	private int seconds; 
	private String trip_id;
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public String getRun_id() {
		return run_id;
	}
	public void setRun_id(String run_id) {
		this.run_id = run_id;
	}
	public boolean isIs_departing() {
		return is_departing;
	}
	public void setIs_departing(boolean is_departing) {
		this.is_departing = is_departing;
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
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	} 

	
}
