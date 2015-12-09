package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;
import nju.express.po.Job;
import nju.express.po.User;

public interface UserblService {
	User login(String name, String password);

	Vector<User> getAll();

	Vector<User> getBySql(String sql, Object[] values);

	User getById(int id);

	int add(User user);

	boolean update(User user);

	boolean delete(int id);
	
	Vector<Job> getJob();
	
	Vector<Department> getDepartment();

}
