package nju.express.blservice;

import java.util.Vector;

import nju.express.vo.MyOrder;

public interface MyOrderblservice {
	Vector<MyOrder> getAll();

	MyOrder getById(int id);

	MyOrder add(MyOrder order);

	MyOrder update(MyOrder order);

	boolean delete(int id);

	Vector<MyOrder> getListBySql(String sql);
}
