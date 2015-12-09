package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3982185743208761844L;
	/**
	 * 
	 */

	public static String ECONOMY_EXPRESS = "经济快递";
	public static String STANDARD_EXPRESS = "标准快递";
	public static String SPECIAL_EXPRESS = "特快专递";

	private int id;
	private String barcode;
	private int sender_id_fk;
	private int receiver_id_fk;
	private int goods_id_fk;
	private int user_id_fk;
	private String post_type;
	private int packingexpense;
	private double collectionfare;
	private Timestamp post_setupdatetime;
	private Timestamp post_estimateddatetime;
	private int BItransport_id_fk;
	private int IItransport_id_fk;
	private int IBtransport_id_fk;
	private int start_stock_id_fk;
	private int end_stock_id_fk;
	private int delivery_id_fk;
	private int recipient_id_fk;
	// 外键需要的类
	private Sender sender;
	private Receiver receiver;
	private Goods goods;
	private User user;
	private BItransport bItransport;
	private IItransport iItransport;
	private BItransport iBtransport;
	private Stock start_stock;
	private Stock end_stock;
	private Delivery delivery;
	private Recipient recipient;

	public Post() {
	}

	

	



	public Post(String barcode, int user_id_fk, String post_type, int packingexpense, double collectionfare,
			Timestamp post_setupdatetime, Timestamp post_estimateddatetime, Sender sender, Receiver receiver,
			Goods goods) {
		this.barcode = barcode;
		this.user_id_fk = user_id_fk;
		this.post_type = post_type;
		this.packingexpense = packingexpense;
		this.collectionfare = collectionfare;
		this.post_setupdatetime = post_setupdatetime;
		this.post_estimateddatetime = post_estimateddatetime;
		this.sender = sender;
		this.receiver = receiver;
		this.goods = goods;
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

	public Timestamp getPost_estimateddatetime() {
		return post_estimateddatetime;
	}

	public void setPost_estimateddatetime(Timestamp post_estimateddatetime) {
		this.post_estimateddatetime = post_estimateddatetime;
	}

	public int getBItransport_id_fk() {
		return BItransport_id_fk;
	}

	public void setBItransport_id_fk(int bItransport_id_fk) {
		BItransport_id_fk = bItransport_id_fk;
	}

	public int getIItransport_id_fk() {
		return IItransport_id_fk;
	}

	public void setIItransport_id_fk(int iItransport_id_fk) {
		IItransport_id_fk = iItransport_id_fk;
	}

	
	public int getStart_stock_id_fk() {
		return start_stock_id_fk;
	}

	public void setStart_stock_id_fk(int start_stock_id_fk) {
		this.start_stock_id_fk = start_stock_id_fk;
	}

	public int getEnd_stock_id_fk() {
		return end_stock_id_fk;
	}

	public void setEnd_stock_id_fk(int end_stock_id_fk) {
		this.end_stock_id_fk = end_stock_id_fk;
	}

	public int getDelivery_id_fk() {
		return delivery_id_fk;
	}

	public void setDelivery_id_fk(int delivery_id_fk) {
		this.delivery_id_fk = delivery_id_fk;
	}

	public int getRecipient_id_fk() {
		return recipient_id_fk;
	}

	public void setRecipient_id_fk(int recipient_id_fk) {
		this.recipient_id_fk = recipient_id_fk;
	}

	public BItransport getbItransport() {
		return bItransport;
	}

	public void setbItransport(BItransport bItransport) {
		this.bItransport = bItransport;
	}

	public IItransport getiItransport() {
		return iItransport;
	}

	public void setiItransport(IItransport iItransport) {
		this.iItransport = iItransport;
	}

	public BItransport getiBtransport() {
		return iBtransport;
	}

	public void setiBtransport(BItransport iBtransport) {
		this.iBtransport = iBtransport;
	}

	public Stock getStart_stock() {
		return start_stock;
	}

	public void setStart_stock(Stock start_stock) {
		this.start_stock = start_stock;
	}

	public Stock getEnd_stock() {
		return end_stock;
	}

	public void setEnd_stock(Stock end_stock) {
		this.end_stock = end_stock;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public int getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public double getCollectionfare() {
		return collectionfare;
	}

	public void setCollectionfare(double collectionfare) {
		this.collectionfare = collectionfare;
	}

	public Timestamp getPost_setupdatetime() {
		return post_setupdatetime;
	}

	public void setPost_setupdatetime(Timestamp post_setupdatetime) {
		this.post_setupdatetime = post_setupdatetime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}







	public int getIBtransport_id_fk() {
		return IBtransport_id_fk;
	}







	public void setIBtransport_id_fk(int iBtransport_id_fk) {
		IBtransport_id_fk = iBtransport_id_fk;
	}

}
