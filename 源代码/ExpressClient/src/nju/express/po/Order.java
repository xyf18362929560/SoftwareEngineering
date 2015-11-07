package nju.express.po;

import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8826259645365840332L;
	private int id;
	
	private int post_id_fk;
	
	private int BItransport_id_fk;
	private int IItransport_id_fk;
	private int IBtransport_id_fk;
	private int start_stock_id_fk;
	private int end_stock_id_fk;
	private int delivery_id_fk;
	private int recipient_id_fk;
	
	//外键需要的类
	private Post post;
	
	private BItransport bItransport;
	private IItransport iItransport;
	private IBtransport iBtransport;
	private Stock start_stock;
	private Stock end_stock;
	private Delivery delivery;
	private Recipient recipient;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPost_id_fk() {
		return post_id_fk;
	}
	public void setPost_id_fk(int post_id_fk) {
		this.post_id_fk = post_id_fk;
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
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
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
	public IBtransport getiBtransport() {
		return iBtransport;
	}
	public void setiBtransport(IBtransport iBtransport) {
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
	
	
}
