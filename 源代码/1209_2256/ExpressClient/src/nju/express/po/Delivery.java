package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Delivery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222258339857988778L;
	/**
	 * 
	 */

	private int id;

	private Timestamp delivery_datetime;
	private int courier_user_id_fk;
	// 外键需要的类

	public Delivery() {
	}

	public Delivery(Timestamp delivery_datetime, int courier_user_id_fk) {
		this.delivery_datetime = delivery_datetime;
		this.courier_user_id_fk = courier_user_id_fk;
	}

	private User courier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourier_user_id_fk() {
		return courier_user_id_fk;
	}

	public void setCourier_user_id_fk(int courier_user_id_fk) {
		this.courier_user_id_fk = courier_user_id_fk;
	}

	public Timestamp getDelivery_datetime() {
		return delivery_datetime;
	}

	public void setDelivery_datetime(Timestamp delivery_datetime) {
		this.delivery_datetime = delivery_datetime;
	}

	public User getCourier() {
		return courier;
	}

	public void setCourier(User courier) {
		this.courier = courier;
	}

}
