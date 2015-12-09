package nju.express.vo;

public class Collection {
	private int id;
	private String collection_datetime;
	private double collection_amount;
	private int payee_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollection_datetime() {
		return collection_datetime;
	}
	public void setCollection_datetime(String collection_datetime) {
		this.collection_datetime = collection_datetime;
	}
	public double getCollection_amount() {
		return collection_amount;
	}
	public void setCollection_amount(double collection_amount) {
		this.collection_amount = collection_amount;
	}
	public int getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(int payee_name) {
		this.payee_name = payee_name;
	}
	
}
