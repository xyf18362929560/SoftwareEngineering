package nju.express.vo;

public class Post {
	public static String  ECONOMY_EXPRESS="经济快递";
	public static String  STANDARD_EXPRESS="标准快递";
	public static String SPECIAL_EXPRESS="特快专递";
	
	private int id;
	private int sender_id_fk;
	private int receiver_id_fk;
	private int goods_id_fk;
	private String post_type;
	private double packingexpense;
	private String packing_username;
	private String post_datetime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSender_id_fk() {
		return sender_id_fk;
	}
	public void setSender_id_fk(int sender_id_fk) {
		this.sender_id_fk = sender_id_fk;
	}
	public int getReceiver_id_fk() {
		return receiver_id_fk;
	}
	public void setReceiver_id_fk(int receiver_id_fk) {
		this.receiver_id_fk = receiver_id_fk;
	}
	public int getGoods_id_fk() {
		return goods_id_fk;
	}
	public void setGoods_id_fk(int goods_id_fk) {
		this.goods_id_fk = goods_id_fk;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	public double getPackingexpense() {
		return packingexpense;
	}
	public void setPackingexpense(double packingexpense) {
		this.packingexpense = packingexpense;
	}
	public String getPacking_username() {
		return packing_username;
	}
	public void setPacking_username(String packing_username) {
		this.packing_username = packing_username;
	}
	public String getPost_datetime() {
		return post_datetime;
	}
	public void setPost_datetime(String post_datetime) {
		this.post_datetime = post_datetime;
	}
	
	
}
