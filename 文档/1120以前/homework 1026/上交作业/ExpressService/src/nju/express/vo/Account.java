package nju.express.vo;

public class Account {
	private int id;
	private String account_name;
	private double account_amount;
	private String account_startdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public double getAccount_amount() {
		return account_amount;
	}
	public void setAccount_amount(double account_amount) {
		this.account_amount = account_amount;
	}
	public String getAccount_startdate() {
		return account_startdate;
	}
	public void setAccount_startdate(String account_startdate) {
		this.account_startdate = account_startdate;
	}
	
}
