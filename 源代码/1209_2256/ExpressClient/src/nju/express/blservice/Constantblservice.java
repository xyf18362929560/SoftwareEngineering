package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;
import nju.express.po.Fare;
import nju.express.po.Job;

public interface Constantblservice {
	Vector<Department> getCenter();
	
	Vector<Job> getJob();
	
	Vector<Fare> getFare();
	
	Vector<Fare> getFareBySql(String sql,Object[] values);
	
	int addFare(Fare fare);
	
	boolean updateFare(String sql,Object[] values);
	
	boolean updateJobSalary(String sql, Object[] values);
}
