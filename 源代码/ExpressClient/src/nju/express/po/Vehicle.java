package nju.express.po;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8693690102522090807L;
	private int id;
	private int department_id_fk;
	private String vehicle_num;
	private String license_plate_number;
	private Date usetime;
	private String vehicle_info;
	
	Department department;
	
	public Vehicle() {
		
	}
	
	public Vehicle(int department_id_fk, String vehicle_num, String license_plate_number, Date usetime,
			String vehicle_info) {
		
		this.department_id_fk = department_id_fk;
		this.vehicle_num = vehicle_num;
		this.license_plate_number = license_plate_number;
		this.usetime = usetime;
		this.vehicle_info = vehicle_info;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLicense_plate_number() {
		return license_plate_number;
	}
	public void setLicense_plate_number(String license_plate_number) {
		this.license_plate_number = license_plate_number;
	}
	public Date getUsetime() {
		return usetime;
	}
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	public String getVehicle_info() {
		return vehicle_info;
	}
	public void setVehicle_info(String vehicle_info) {
		this.vehicle_info = vehicle_info;
	}
	public String getVehicle_num() {
		return vehicle_num;
	}
	public void setVehicle_num(String vehicle_num) {
		this.vehicle_num = vehicle_num;
	}
	public int getDepartment_id_fk() {
		return department_id_fk;
	}
	public void setDepartment_id_fk(int department_id_fk) {
		this.department_id_fk = department_id_fk;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}
