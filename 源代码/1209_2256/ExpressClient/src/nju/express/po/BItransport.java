package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class BItransport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9182658462538724541L;
	/**
	 * 
	 */

	private int id;
	//当flag为1时，表示BI，当flag为2时，表示IB
	private int flag;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private Timestamp BItransport_datetime;

	private int vehicle_id_fk;
	private int driver_id_fk;
	private double BItransport_fare;
	private String BItransport_info;

	// 外键需要的类

	private Department start_department;
	private Department end_department;
	private Vehicle vehicle;
	private Driver driver;

	public BItransport() {
	}

	public BItransport(int flag,int start_department_id_fk, int end_department_id_fk, Timestamp bItransport_datetime,
			int vehicle_id_fk, int driver_id_fk, double bItransport_fare, String bItransport_info) {
		this.flag=flag;
		this.start_department_id_fk = start_department_id_fk;
		this.end_department_id_fk = end_department_id_fk;
		BItransport_datetime = bItransport_datetime;
		this.vehicle_id_fk = vehicle_id_fk;
		this.driver_id_fk = driver_id_fk;
		BItransport_fare = bItransport_fare;
		BItransport_info = bItransport_info;
	}

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

	public Timestamp getBItransport_datetime() {
		return BItransport_datetime;
	}

	public void setBItransport_datetime(Timestamp bItransport_datetime) {
		BItransport_datetime = bItransport_datetime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
