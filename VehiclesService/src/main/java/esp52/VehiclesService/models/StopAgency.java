package esp52.VehiclesService.models;

public class StopAgency {
	private String stop_id;
	private String agency_id;
	
	public StopAgency() {}
	
	public StopAgency(String stop_id, String agency_id) {
		this.stop_id = stop_id;
		this.agency_id = agency_id;
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

	@Override
	public boolean equals(Object obj) {
		StopAgency s = (StopAgency) obj;
		if(s.getAgency_id() != this.agency_id)
			return false;
		if(s.getStop_id() != this.stop_id)
			return false;
		return true;
	} 

	
}
