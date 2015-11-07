package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Delivery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222258339857988778L;
	/**
	 * 
	 */

	private int id;
	private Timestamp arrive_time;
	private int courier_user_id_fk;
	//外键需要的类
	private User courier_user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getArrive_time() {
		return arrive_time;
	}
	public void setArrive_time(Timestamp arrive_time) {
		this.arrive_time = arrive_time;
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

	
}
