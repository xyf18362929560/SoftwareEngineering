package nju.express.po;

import java.io.Serializable;

public class Goods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5883761287646469174L;
	private int id;
	private String goods_name;
	private int goods_num;
	private double goods_weight;
	private double goods_length;
	private double goods_width;
	private double goods_height;
	private String goods_info;

	public Goods() {
	}

	public Goods(String goods_name, int goods_num, double goods_weight, double goods_length, double goods_width,
			double goods_height, String goods_info) {
		super();
		this.goods_name = goods_name;
		this.goods_num = goods_num;
		this.goods_weight = goods_weight;
		this.goods_length = goods_length;
		this.goods_width = goods_width;
		this.goods_height = goods_height;
		this.goods_info = goods_info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}

	public double getGoods_weight() {
		return goods_weight;
	}

	public void setGoods_weight(double goods_weight) {
		this.goods_weight = goods_weight;
	}

	public double getGoods_length() {
		return goods_length;
	}

	public void setGoods_length(double goods_length) {
		this.goods_length = goods_length;
	}

	public double getGoods_width() {
		return goods_width;
	}

	public void setGoods_width(double goods_width) {
		this.goods_width = goods_width;
	}

	public double getGoods_height() {
		return goods_height;
	}

	public void setGoods_height(double goods_height) {
		this.goods_height = goods_height;
	}

	public String getGoods_info() {
		return goods_info;
	}

	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
	}
}
