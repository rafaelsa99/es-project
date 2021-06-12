package esp52.ManagementService.models;


public class ParkingLotationFree {
	
	private int id;
	private String name; // Parking lot identifier
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
