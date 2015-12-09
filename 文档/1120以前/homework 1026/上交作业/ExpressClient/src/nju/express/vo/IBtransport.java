package nju.express.vo;

public class IBtransport {
	private int id;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private String IBtransport_start_datetime;
	private String IBtransport_end_datetime;
	private int vehicle_id_fk;
	private int driver_id_fk;
	private double IBtransport_fare;
	private String IBtransport_info;
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
	public String getIBtransport_start_datetime() {
		return IBtransport_start_datetime;
	}
	public void setIBtransport_start_datetime(String iBtransport_start_datetime) {
		IBtransport_start_datetime = iBtransport_start_datetime;
	}
	public String getIBtransport_end_datetime() {
		return IBtransport_end_datetime;
	}
	public void setIBtransport_end_datetime(String iBtransport_end_datetime) {
		IBtransport_end_datetime = iBtransport_end_datetime;
	}
	public double getIBtransport_fare() {
		return IBtransport_fare;
	}
	public void setIBtransport_fare(double iBtransport_fare) {
		IBtransport_fare = iBtransport_fare;
	}
	
}
