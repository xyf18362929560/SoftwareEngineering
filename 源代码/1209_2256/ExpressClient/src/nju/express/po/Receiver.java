package nju.express.po;

import java.io.Serializable;

public class Receiver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4373624797126494785L;
	private int id;
	private String receiver_name;
	private String receiver_address;
	private String receiver_phone;
	private String receiver_info;

	public Receiver() {
	}

	public Receiver(String receiver_name, String receiver_address, String receiver_phone, String receiver_info) {

		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.receiver_phone = receiver_phone;
		this.receiver_info = receiver_info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_address() {
		return receiver_address;
	}

	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public String getReceiver_info() {
		return receiver_info;
	}

	public void setReceiver_info(String receiver_info) {
		this.receiver_info = receiver_info;
	}
}
