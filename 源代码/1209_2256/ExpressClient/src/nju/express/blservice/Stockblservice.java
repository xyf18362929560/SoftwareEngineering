package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Stock;

public interface Stockblservice {
	Vector<Stock> getAll();

	Vector<Stock> getBySql(String sql, Object[] values);

	Stock getById(int id);

	int add(Stock stock);

	boolean update(Stock stock);

	boolean delete(int id);
}
