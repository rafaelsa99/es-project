package esp52.ManagementService.models;

public class VehiclesRoute {
	
	private int id;
	private String route_id;
	private int num_vehicles;
	private String route_name;
	
	public VehiclesRoute(){}

	public VehiclesRoute(String route_id) {
		super();
		this.route_id = route_id;
		this.num_vehicles = 1;
	}
	
	public VehiclesRoute(int id, String route_id, int num_vehicles, String route_name) {
		super();
		this.id = id;
		this.route_id = route_id;
		this.num_vehicles = num_vehicles;
		this.route_name = route_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public int getNum_vehicles() {
		return num_vehicles;
	}
	public void setNum_vehicles(int num_vehicles) {
		this.num_vehicles = num_vehicles;
	}
	
	public void incrementNumVehicles() {
		this.num_vehicles++;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	
	
}
