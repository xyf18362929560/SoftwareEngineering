package nju.express.vo;

public class Recipient {
	private int id;
	private String recipient_name;
	private String recipient_datetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getRecipient_datetime() {
		return recipient_datetime;
	}
	public void setRecipient_datetime(String recipient_datetime) {
		this.recipient_datetime = recipient_datetime;
	}
	
}
