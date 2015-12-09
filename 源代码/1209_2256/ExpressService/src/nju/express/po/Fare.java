package nju.express.po;

import java.io.Serializable;

public class Fare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190888217343358080L;
	
	private int id;
	private int start_department_id_fk;
	private int end_department_id_fk;
	private double economyfare;
	private double standardfare;
	private double specialfare;
	private Department start_department;
	private Department end_department;
	
	public Fare() {
	}
	
	public Fare(int start_department_id_fk, int end_department_id_fk, double economyfare, double standardfare,
			double specialfare) {
		this.start_department_id_fk = start_department_id_fk;
		this.end_department_id_fk = end_department_id_fk;
		this.economyfare = economyfare;
		this.standardfare = standardfare;
		this.specialfare = specialfare;
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
	public double getEconomyfare() {
		return economyfare;
	}
	public void setEconomyfare(double economyfare) {
		this.economyfare = economyfare;
	}
	public double getStandardfare() {
		return standardfare;
	}
	public void setStandardfare(double standardfare) {
		this.standardfare = standardfare;
	}
	public double getSpecialfare() {
		return specialfare;
	}
	public void setSpecialfare(double specialfare) {
		this.specialfare = specialfare;
	}
	
}
