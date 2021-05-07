package com.es.projectbackend.model;

public class ParkingLot {
	private String id; // Parking lot identifier
	private String name; // Name of the parking lot
	private int disabledtotal; //Total disabled parking places
	private int disabledfree; //Number of free disabled parking places
	private int free; //Number of free parking places
	private int total; //Total parking places
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDisabledtotal() {
		return disabledtotal;
	}
	public void setDisabledtotal(int disabledtotal) {
		this.disabledtotal = disabledtotal;
	}
	public int getDisabledfree() {
		return disabledfree;
	}
	public void setDisabledfree(int disabledfree) {
		this.disabledfree = disabledfree;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
