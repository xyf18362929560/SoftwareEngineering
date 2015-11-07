package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Recipient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3676433693463313604L;
	private int id;
	private String recipient_name;
	private Timestamp recipient_datetime;
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
	public Timestamp getRecipient_datetime() {
		return recipient_datetime;
	}
	public void setRecipient_datetime(Timestamp recipient_datetime) {
		this.recipient_datetime = recipient_datetime;
	}
	
}
