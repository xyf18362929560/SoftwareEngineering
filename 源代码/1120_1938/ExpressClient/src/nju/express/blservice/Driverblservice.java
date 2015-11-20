package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;
import nju.express.po.Driver;

public interface Driverblservice {
	Vector<Driver> getAll();

	// Vector<Driver> getByCondition(String columnname, Object value);
	//
	// Vector<Vehicle> getBySql(String sql);

	Driver getById(int id);

	int add(Driver driver);

	boolean update(Driver driver);

	boolean delete(int id);

	Vector<Department> getBusiness();
}
