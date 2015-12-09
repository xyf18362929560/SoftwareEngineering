package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 86588551549839830L;
	public static String RENT="租金";
	public static String FREIGHT="运费";
	public static String SALARY="工资";
	public static String BOMUS="奖励";
	
	private int id;
	private Timestamp payment_datetime;
	private double payment_amount;
	private int  finance_user_id_fk;
	private String payment_type;
	private String payment_info;
	
	//外键需要的类
	private User finance_user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getPayment_datetime() {
		return payment_datetime;
	}
	public void setPayment_datetime(Timestamp payment_datetime) {
		this.payment_datetime = payment_datetime;
	}
	public double getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}
	
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getPayment_info() {
		return payment_info;
	}
	public void setPayment_info(String payment_info) {
		this.payment_info = payment_info;
	}
	public int getFinance_user_id_fk() {
		return finance_user_id_fk;
	}
	public void setFinance_user_id_fk(int finance_user_id_fk) {
		this.finance_user_id_fk = finance_user_id_fk;
	}
	public User getFinance_user() {
		return finance_user;
	}
	public void setFinance_user(User finance_user) {
		this.finance_user = finance_user;
	}
	
}
