package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;
import nju.express.po.Vehicle;

public interface Vehicleblservice {
	Vector<Vehicle> getAll();
	
	Vector<Vehicle> getByCondition(String columnname,Object value);
	
	Vector<Vehicle> getBySql(String sql);

	Vehicle getById(int id);

	Vehicle add(Vehicle vehicle);

	Vehicle update(Vehicle vehicle);

	boolean delete(int id);
	
	Vector<Department> getBusiness();
}
