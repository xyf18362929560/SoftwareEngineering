package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;
import nju.express.po.Driver;

public interface Driverblservice {
	Vector<Driver> getAll();

	Vector<Driver> getBySql(String sql, Object[] values);

	Driver getById(int id);

	int add(Driver driver);

	boolean update(Driver driver);

	boolean delete(int id);

	Vector<Department> getBusiness();
}
