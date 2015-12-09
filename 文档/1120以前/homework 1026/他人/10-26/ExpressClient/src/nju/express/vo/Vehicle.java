package nju.express.vo;

public class Vehicle {
	private int id;
	private int license_plate_number;
	private String usetime;
	private String vehicle_info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLicense_plate_number() {
		return license_plate_number;
	}
	public void setLicense_plate_number(int license_plate_number) {
		this.license_plate_number = license_plate_number;
	}
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	public String getVehicle_info() {
		return vehicle_info;
	}
	public void setVehicle_info(String vehicle_info) {
		this.vehicle_info = vehicle_info;
	}
}
