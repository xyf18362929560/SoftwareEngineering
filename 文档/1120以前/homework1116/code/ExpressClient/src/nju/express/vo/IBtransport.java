package nju.express.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class IBtransport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3656815392431619698L;
	private int id;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private Timestamp IBtransport_start_datetime;
	private Timestamp IBtransport_end_datetime;
	private int vehicle_id_fk;
	private int driver_id_fk;
	private double IBtransport_fare;
	private String IBtransport_info;
	
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
	public String getIBtransport_info() {
		return IBtransport_info;
	}
	public void setIBtransport_info(String iBtransport_info) {
		IBtransport_info = iBtransport_info;
	}
	public Timestamp getIBtransport_start_datetime() {
		return IBtransport_start_datetime;
	}
	public void setIBtransport_start_datetime(Timestamp iBtransport_start_datetime) {
		IBtransport_start_datetime = iBtransport_start_datetime;
	}
	public Timestamp getIBtransport_end_datetime() {
		return IBtransport_end_datetime;
	}
	public void setIBtransport_end_datetime(Timestamp iBtransport_end_datetime) {
		IBtransport_end_datetime = iBtransport_end_datetime;
	}
	public double getIBtransport_fare() {
		return IBtransport_fare;
	}
	public void setIBtransport_fare(double iBtransport_fare) {
		IBtransport_fare = iBtransport_fare;
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
