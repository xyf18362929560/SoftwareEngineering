package nju.express.po;

import java.io.Serializable;
import java.sql.Date;

public class Driver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1828929968651278984L;
	private int id;
	private int department_id_fk;
	private String driver_name;
	private String driver_num;
	private String driver_gender;
	private Date driver_birthday;
	private String driver_idcard;
	private String driver_phone;

	Department department;

	public Driver() {

	}

	public Driver(int department_id_fk, String driver_name, String driver_num, String driver_gender,
			Date driver_birthday, String driver_idcard, String driver_phone) {

		this.department_id_fk = department_id_fk;
		this.driver_name = driver_name;
		this.driver_num = driver_num;
		this.driver_gender = driver_gender;
		this.driver_birthday = driver_birthday;
		this.driver_idcard = driver_idcard;
		this.driver_phone = driver_phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public Date getDriver_birthday() {
		return driver_birthday;
	}

	public void setDriver_birthday(Date driver_birthday) {
		this.driver_birthday = driver_birthday;
	}

	public String getDriver_idcard() {
		return driver_idcard;
	}

	public void setDriver_idcard(String driver_idcard) {
		this.driver_idcard = driver_idcard;
	}

	public String getDriver_phone() {
		return driver_phone;
	}

	public void setDriver_phone(String driver_phone) {
		this.driver_phone = driver_phone;
	}

	public String getDriver_num() {
		return driver_num;
	}

	public void setDriver_num(String driver_num) {
		this.driver_num = driver_num;
	}

	public int getDepartment_id_fk() {
		return department_id_fk;
	}

	public void setDepartment_id_fk(int department_id_fk) {
		this.department_id_fk = department_id_fk;
	}

	public String getDriver_gender() {
		return driver_gender;
	}

	public void setDriver_gender(String driver_gender) {
		this.driver_gender = driver_gender;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
