package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3982185743208761844L;
	/**
	 * 
	 */
	
	public static String  ECONOMY_EXPRESS="经济快递";
	public static String  STANDARD_EXPRESS="标准快递";
	public static String SPECIAL_EXPRESS="特快专递";
	
	private int id;
	private String barcode;
	private int sender_id_fk;
	private int receiver_id_fk;
	private int goods_id_fk;
	private int collection_id_fk;
	private String post_type;
	private int packingexpense;
	
	private Timestamp post_estimateddatetime;
	
	//外键需要的类
	private Sender sender;
	private Receiver receiver;
	private Goods goods;
	private Collection collection;
	public Post() {
	}
	
	public Post(String barcode, String post_type, int packingexpense, Timestamp post_estimateddatetime, Sender sender,
			Receiver receiver, Goods goods, Collection collection) {
		
		this.barcode = barcode;
		this.post_type = post_type;
		this.packingexpense = packingexpense;
		this.post_estimateddatetime = post_estimateddatetime;
		this.sender = sender;
		this.receiver = receiver;
		this.goods = goods;
		this.collection = collection;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	public int getPackingexpense() {
		return packingexpense;
	}
	public void setPackingexpense(int packingexpense) {
		this.packingexpense = packingexpense;
	}
	
	
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getCollection_id_fk() {
		return collection_id_fk;
	}
	public void setCollection_id_fk(int collection_id_fk) {
		this.collection_id_fk = collection_id_fk;
	}
	
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public Timestamp getPost_estimateddatetime() {
		return post_estimateddatetime;
	}
	public void setPost_estimateddatetime(Timestamp post_estimateddatetime) {
		this.post_estimateddatetime = post_estimateddatetime;
	}
	
	
	
	
}
