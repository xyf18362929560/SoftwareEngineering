package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;

public interface Departmentblservice {
	Vector<Department> getAll();

	Vector<Department> getBySql(String sql, Object[] values);

	Department getById(int id);

	int add(Department department);

	boolean update(Department department);

	boolean delete(int id);
}
