package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Payment;
import nju.express.po.User;

public interface Paymentblservice {
	Vector<Payment> getAll();

	Vector<Payment> getBySql(String sql, Object[] values);

	Payment getById(int id);

	int add(Payment payment);

	boolean update(Payment payment);

	boolean delete(int id);

}
