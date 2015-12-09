package nju.express.vo;

public class Payment {
	public static String RENT="租金";
	public static String FREIGHT="运费";
	public static String SALARY="工资";
	public static String BOMUS="奖励";
	
	private int id;
	private String payment_datetime;
	private double payment_amount;
	private String payer_name;
	private String payment_type;
	private String payment_info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayment_datetime() {
		return payment_datetime;
	}
	public void setPayment_datetime(String payment_datetime) {
		this.payment_datetime = payment_datetime;
	}
	public double getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPayer_name() {
		return payer_name;
	}
	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
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
	
}
