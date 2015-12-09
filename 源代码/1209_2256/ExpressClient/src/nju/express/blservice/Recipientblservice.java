package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Recipient;

public interface Recipientblservice {
	Vector<Recipient> getAll();

	Vector<Recipient> getBySql(String sql, Object[] values);

	Recipient getById(int id);

	int add(Recipient recipient);

	boolean update(Recipient recipient);

	boolean delete(int id);

}
