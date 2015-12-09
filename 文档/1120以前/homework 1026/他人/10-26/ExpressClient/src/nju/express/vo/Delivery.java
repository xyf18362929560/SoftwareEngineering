package nju.express.vo;

public class Delivery {
	private int id;
	private String arrive_time;
	private int courier_user_id_fk;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArrive_time() {
		return arrive_time;
	}
	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}
	public int getCourier_user_id_fk() {
		return courier_user_id_fk;
	}
	public void setCourier_user_id_fk(int courier_user_id_fk) {
		this.courier_user_id_fk = courier_user_id_fk;
	}
	
}
