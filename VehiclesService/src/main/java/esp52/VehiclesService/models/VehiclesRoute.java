package esp52.VehiclesService.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "vehiclesRoutes")
public class VehiclesRoute {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name = "route_id")
	private String route_id;
	@Column(name = "num_vehicles")
	private int num_vehicles;
	@Transient
	private String route_name;
	
	public VehiclesRoute(){}

	public VehiclesRoute(String route_id) {
		super();
		this.route_id = route_id;
		this.num_vehicles = 1;
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
