package nju.express.po;

import java.io.Serializable;

public class Sender implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4720241497080865868L;
	private int id;
	private String sender_name;
	private String sender_address;
	private String sender_phone;
	private String sender_info;

	public Sender() {

	}

	public Sender(String sender_name, String sender_address, String sender_phone, String sender_info) {

		this.sender_name = sender_name;
		this.sender_address = sender_address;
		this.sender_phone = sender_phone;
		this.sender_info = sender_info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

	public String getSender_address() {
		return sender_address;
	}

	public void setSender_address(String sender_address) {
		this.sender_address = sender_address;
	}

	public String getSender_phone() {
		return sender_phone;
	}

	public void setSender_phone(String sender_phone) {
		this.sender_phone = sender_phone;
	}

	public String getSender_info() {
		return sender_info;
	}

	public void setSender_info(String sender_info) {
		this.sender_info = sender_info;
	}

}
