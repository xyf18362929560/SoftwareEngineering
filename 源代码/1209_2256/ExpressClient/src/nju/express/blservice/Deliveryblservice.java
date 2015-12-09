package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Delivery;
import nju.express.po.User;

public interface Deliveryblservice {
	Vector<Delivery> getAll();

	Vector<Delivery> getBySql(String sql, Object[] values);

	Delivery getById(int id);

	int add(Delivery delivery);

	boolean update(Delivery delivery);

	boolean delete(int id);

	Vector<User> getCourier(int department_id);
}
