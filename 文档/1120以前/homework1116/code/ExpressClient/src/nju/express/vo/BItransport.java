package nju.express.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BItransport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9182658462538724541L;
	/**
	 * 
	 */

	private int id;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private Timestamp BItransport_start_datetime;
	private Timestamp BItransport_end_datetime;
	private int vehicle_id_fk;
	private int driver_id_fk;
	private double BItransport_fare;
	private String BItransport_info;
	
	//外键需要的类
	private Department start_department;
	private Department end_department;
	private Vehicle vehicle;
	private Driver driver;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStart_department_id_fk() {
		return start_department_id_fk;
	}
	public void setStart_department_id_fk(int start_department_id_fk) {
		this.start_department_id_fk = start_department_id_fk;
	}
	public int getEnd_department_id_fk() {
		return end_department_id_fk;
	}
	public void setEnd_department_id_fk(int end_department_id_fk) {
		this.end_department_id_fk = end_department_id_fk;
	}
	public Timestamp getBItransport_start_datetime() {
		return BItransport_start_datetime;
	}
	public void setBItransport_start_datetime(Timestamp bItransport_start_datetime) {
		BItransport_start_datetime = bItransport_start_datetime;
	}
	public Timestamp getBItransport_end_datetime() {
		return BItransport_end_datetime;
	}
	public void setBItransport_end_datetime(Timestamp bItransport_end_datetime) {
		BItransport_end_datetime = bItransport_end_datetime;
	}
	public int getVehicle_id_fk() {
		return vehicle_id_fk;
	}
	public void setVehicle_id_fk(int vehicle_id_fk) {
		this.vehicle_id_fk = vehicle_id_fk;
	}
	public int getDriver_id_fk() {
		return driver_id_fk;
	}
	public void setDriver_id_fk(int driver_id_fk) {
		this.driver_id_fk = driver_id_fk;
	}
	public double getBItransport_fare() {
		return BItransport_fare;
	}
	public void setBItransport_fare(double bItransport_fare) {
		BItransport_fare = bItransport_fare;
	}
	public String getBItransport_info() {
		return BItransport_info;
	}
	public void setBItransport_info(String bItransport_info) {
		BItransport_info = bItransport_info;
	}
	public Department getStart_department() {
		return start_department;
	}
	public void setStart_department(Department start_department) {
		this.start_department = start_department;
	}
	public Department getEnd_department() {
		return end_department;
	}
	public void setEnd_department(Department end_department) {
		this.end_department = end_department;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	
	
}
