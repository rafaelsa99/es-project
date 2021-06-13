package esp52.ParkingLotsService.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parkingLotation")
public class ParkingLotationFree {
	
	@Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name = "parking_name")
	private String name; // Parking lot identifier
	@Column(name = "free_spaces")
	private int free; //Number of free parking places
	
	public ParkingLotationFree(){}
	
	public ParkingLotationFree(String name, int free) {
		super();
		this.name = name;
		this.free = free;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
