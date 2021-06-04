package esp52.ManagementService.models;

import java.util.Collection;

public class StopInfo {
	private String display_name; 
	private Double latitude; 
	private Double longitude;
	private Collection<PredictionInfo> predictions;
	
	public StopInfo() {}
	
	public StopInfo(String display_name, Double latitude, Double longitude, Collection<PredictionInfo> predictions) {
		this.display_name = display_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.predictions = predictions;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
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
	public Collection<PredictionInfo> getPredictions() {
		return predictions;
	}
	public void setPredictions(Collection<PredictionInfo> predictions) {
		this.predictions = predictions;
	}
	
	
}
