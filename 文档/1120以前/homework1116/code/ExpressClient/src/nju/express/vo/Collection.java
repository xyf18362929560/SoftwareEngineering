package nju.express.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Collection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7433096702219953850L;
	/**
	 * 
	 */

	private int id;
	private Timestamp collection_datetime;
	private double collection_amount;
	private int courier_user_id_fk;
	private int department_id_fk;
	// 外键需要的类
	private User courier_user;
	private Department department;

	public Collection() {
	}

	public Collection(Timestamp collection_datetime, double collection_amount, int courier_user_id_fk) {

		this.collection_datetime = collection_datetime;
		this.collection_amount = collection_amount;
		this.courier_user_id_fk = courier_user_id_fk;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCollection_datetime() {
		return collection_datetime;
	}

	public void setCollection_datetime(Timestamp collection_datetime) {
		this.collection_datetime = collection_datetime;
	}

	public double getCollection_amount() {
		return collection_amount;
	}

	public void setCollection_amount(double collection_amount) {
		this.collection_amount = collection_amount;
	}

	public int getCourier_user_id_fk() {
		return courier_user_id_fk;
	}

	public void setCourier_user_id_fk(int courier_user_id_fk) {
		this.courier_user_id_fk = courier_user_id_fk;
	}

	public User getCourier_user() {
		return courier_user;
	}

	public void setCourier_user(User courier_user) {
		this.courier_user = courier_user;
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
