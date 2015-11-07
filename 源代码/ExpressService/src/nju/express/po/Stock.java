package nju.express.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class Stock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2417119450723867435L;
	private int id;
	private int department_id_fk;
	private int area_num;
	private int row_num;
	private int shelf_num;
	private int position_num;
	private Timestamp stockin_datetime;
	private Timestamp stockout_datetime;
	private int stock_isEmpty;
	//外键需要的类
	private Department department;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartment_id_fk() {
		return department_id_fk;
	}
	public void setDepartment_id_fk(int department_id_fk) {
		this.department_id_fk = department_id_fk;
	}
	public int getArea_num() {
		return area_num;
	}
	public void setArea_num(int area_num) {
		this.area_num = area_num;
	}
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public int getShelf_num() {
		return shelf_num;
	}
	public void setShelf_num(int shelf_num) {
		this.shelf_num = shelf_num;
	}
	public int getPosition_num() {
		return position_num;
	}
	public void setPosition_num(int position_num) {
		this.position_num = position_num;
	}
	public Timestamp getStockin_datetime() {
		return stockin_datetime;
	}
	public void setStockin_datetime(Timestamp stockin_datetime) {
		this.stockin_datetime = stockin_datetime;
	}
	public Timestamp getStockout_datetime() {
		return stockout_datetime;
	}
	public void setStockout_datetime(Timestamp stockout_datetime) {
		this.stockout_datetime = stockout_datetime;
	}
	public int isStock_isEmpty() {
		return stock_isEmpty;
	}
	public void setStock_isEmpty(int stock_isEmpty) {
		this.stock_isEmpty = stock_isEmpty;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public int getStock_isEmpty() {
		return stock_isEmpty;
	}
	
	
}
