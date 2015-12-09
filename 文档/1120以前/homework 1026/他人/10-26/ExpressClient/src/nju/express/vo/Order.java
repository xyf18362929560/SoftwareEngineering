package nju.express.vo;

public class Order {
	private int id;
	private String order_barcode;
	private int post_id_fk;
	private int collection_id_fk;
	private int BItransport_id_fk;
	private int IItransport_id_fk;
	private int IBtransport_id_fk;
	private int start_stock_id_fk;
	private int end_stock_id_fk;
	private int delivery_id_fk;
	private int recipient_id_fk;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_barcode() {
		return order_barcode;
	}
	public void setOrder_barcode(String order_barcode) {
		this.order_barcode = order_barcode;
	}
	public int getPost_id_fk() {
		return post_id_fk;
	}
	public void setPost_id_fk(int post_id_fk) {
		this.post_id_fk = post_id_fk;
	}
	public int getCollection_id_fk() {
		return collection_id_fk;
	}
	public void setCollection_id_fk(int collection_id_fk) {
		this.collection_id_fk = collection_id_fk;
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
	public int getIBtransport_id_fk() {
		return IBtransport_id_fk;
	}
	public void setIBtransport_id_fk(int iBtransport_id_fk) {
		IBtransport_id_fk = iBtransport_id_fk;
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
	
	
}
