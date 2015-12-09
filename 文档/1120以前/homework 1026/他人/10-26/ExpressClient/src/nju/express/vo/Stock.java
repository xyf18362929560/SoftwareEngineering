package nju.express.vo;

public class Stock {
	private int id;
	private int department_id_fk;
	private int area_num;
	private int row_num;
	private int shelf_num;
	private int position_num;
	private String stockin_datetime;
	private String stockout_datetime;
	private int stock_isEmpty;
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
	public String getStockin_datetime() {
		return stockin_datetime;
	}
	public void setStockin_datetime(String stockin_datetime) {
		this.stockin_datetime = stockin_datetime;
	}
	public String getStockout_datetime() {
		return stockout_datetime;
	}
	public void setStockout_datetime(String stockout_datetime) {
		this.stockout_datetime = stockout_datetime;
	}
	public int isStock_isEmpty() {
		return stock_isEmpty;
	}
	public void setStock_isEmpty(int stock_isEmpty) {
		this.stock_isEmpty = stock_isEmpty;
	}
	
	
}
