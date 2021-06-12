package esp52.externaAPI.models;

public class Agency {
	private String id;
	private String display_name; 
	private String localtime;
	
	public Agency() {}
	
	public Agency(String id, String display_name) {
		super();
		this.id = id;
		this.display_name = display_name;
		this.localtime = "";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getLocaltime() {
		return localtime;
	}
	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	} 
	
	
}
