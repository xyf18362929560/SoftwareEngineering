package nju.express.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class IItransport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1980896430358374048L;
	public static String PLANE="飞机";
	public static String RAILWAY="铁路";
	public static String ROAD="公路";
	private int id;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private Timestamp IItransport_start_datetime;
	private Timestamp IItransport_end_datetime;
	private double IItransport_fare;
	private String IItransport_type;
	private String IItransport_info;
	
	//外键需要的类
	private Department start_department;
	private Department end_department;
	
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
	
	public String getIItransport_type() {
		return IItransport_type;
	}
	public void setIItransport_type(String iItransport_type) {
		IItransport_type = iItransport_type;
	}
	public String getIItransport_info() {
		return IItransport_info;
	}
	public void setIItransport_info(String iItransport_info) {
		IItransport_info = iItransport_info;
	}
	public Timestamp getIItransport_start_datetime() {
		return IItransport_start_datetime;
	}
	public void setIItransport_start_datetime(Timestamp iItransport_start_datetime) {
		IItransport_start_datetime = iItransport_start_datetime;
	}
	public Timestamp getIItransport_end_datetime() {
		return IItransport_end_datetime;
	}
	public void setIItransport_end_datetime(Timestamp iItransport_end_datetime) {
		IItransport_end_datetime = iItransport_end_datetime;
	}
	public double getIItransport_fare() {
		return IItransport_fare;
	}
	public void setIItransport_fare(double iItransport_fare) {
		IItransport_fare = iItransport_fare;
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
	
}
