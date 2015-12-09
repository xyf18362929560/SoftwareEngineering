package nju.express.vo;

public class BItransport {
	private int id;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private String BItransport_datetime;
	private int vehicle_id_fk;
	private int driver_id_fk;
	private String BItransport_info;
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
	public String getBItransport_datetime() {
		return BItransport_datetime;
	}
	public void setBItransport_datetime(String bItransport_datetime) {
		BItransport_datetime = bItransport_datetime;
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
	public String getBItransport_info() {
		return BItransport_info;
	}
	public void setBItransport_info(String bItransport_info) {
		BItransport_info = bItransport_info;
	}
	
}
