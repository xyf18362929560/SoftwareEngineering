package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7867499505836145269L;
	/**
	 * 
	 */

	private int id;
	private String account_name;
	private double account_amount;
	private Timestamp account_startdatetime;
	
	public Account() {
	}
	

	public Account(String account_name, double account_amount, Timestamp account_startdatetime) {
		this.account_name = account_name;
		this.account_amount = account_amount;
		this.account_startdatetime = account_startdatetime;
	}


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

	public Timestamp getAccount_startdatetime() {
		return account_startdatetime;
	}

	public void setAccount_startdatetime(Timestamp account_startdatetime) {
		this.account_startdatetime = account_startdatetime;
	}

}
