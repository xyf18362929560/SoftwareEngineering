package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Recipient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3676433693463313604L;
	private int id;
	private int post_id_fk;
	private String recipient_name;
	private Timestamp recipient_datetime;
	//外键需要的类
	private Post post;
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
	public int getPost_id_fk() {
		return post_id_fk;
	}
	public void setPost_id_fk(int post_id_fk) {
		this.post_id_fk = post_id_fk;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
}
