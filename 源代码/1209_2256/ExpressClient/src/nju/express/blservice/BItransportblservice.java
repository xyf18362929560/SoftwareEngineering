package nju.express.blservice;

import java.util.Vector;

import nju.express.po.BItransport;
import nju.express.po.Department;
import nju.express.po.Driver;
import nju.express.po.Vehicle;

public interface BItransportblservice {
	Vector<BItransport> getAll();

	Vector<BItransport> getBySql(String sql, Object[] values);

	BItransport getById(int id);

	int add(BItransport bItransport);

	boolean update(BItransport bItransport);

	boolean delete(int id);

	Vector<Department> getBusiness();

	Vector<Department> getCenter();

	Vector<Vehicle> getVehicle(int department_id);

	Vector<Driver> getDriver(int department_id);
}
