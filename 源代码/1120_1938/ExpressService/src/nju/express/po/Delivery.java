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
	private int post_id_fk;
	private Timestamp delivery_time;
	private int courier_user_id_fk;
	//外键需要的类
	private Post post;
	private User courier_user;
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
	public User getCourier_user() {
		return courier_user;
	}
	public void setCourier_user(User courier_user) {
		this.courier_user = courier_user;
	}
	public Timestamp getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(Timestamp delivery_time) {
		this.delivery_time = delivery_time;
	}
	public int getPost_id_fk() {
		return post_id_fk;
	}
	public void setPost_id_fk(int post_id_fk) {
		this.post_id_fk = post_id_fk;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}

	
}
