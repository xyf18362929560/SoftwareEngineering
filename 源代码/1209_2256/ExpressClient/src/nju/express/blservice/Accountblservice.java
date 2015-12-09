package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Account;

public interface Accountblservice {
	Vector<Account> getAll();

	Vector<Account> getBySql(String sql, Object[] values);

	Account getById(int id);

	int add(Account account);

	boolean update(Account account);

	boolean delete(int id);
}
