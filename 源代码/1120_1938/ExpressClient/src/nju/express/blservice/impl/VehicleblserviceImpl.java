package nju.express.blservice.impl;

import java.util.Vector;

import nju.express.blservice.Vehicleblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.po.Department;
import nju.express.po.Vehicle;

public class VehicleblserviceImpl implements Vehicleblservice {
	VehicleDataService vehicleDataService;
	DepartmentDataService departmentDataService;

	public VehicleblserviceImpl(VehicleDataService vehicleDataService, DepartmentDataService departmentDataService) {

		this.vehicleDataService = vehicleDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<Vehicle> getAll() {
		Vector<Vehicle> vehicles = null;
		try {
			vehicles = vehicleDataService.getAll();
			for (Vehicle vehicle : vehicles) {
				vehicle.setDepartment(departmentDataService.getById(vehicle.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicles;
	}

	@Override
	public Vehicle getById(int id) {
		Vehicle vehicle = null;
		try {
			vehicle = vehicleDataService.getById(id);

			vehicle.setDepartment(departmentDataService.getById(vehicle.getDepartment_id_fk()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	@Override
	public int add(Vehicle vehicle) {
		int result = 0;
		try {
			result = vehicleDataService.insert(vehicle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Vehicle vehicle) {
		boolean result = false;
		try {
			result = vehicleDataService.update(vehicle);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = vehicleDataService.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<Department> getBusiness() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getDepartmentOfType(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public Vector<Vehicle> getByCondition(String columnname, Object value) {
		Vector<Vehicle> vehicles = null;
		try {
			vehicles = vehicleDataService.getByCondition(columnname, value);
			for (Vehicle vehicle : vehicles) {
				vehicle.setDepartment(departmentDataService.getById(vehicle.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicles;
	}

	@Override
	public Vector<Vehicle> getBySql(String sql) {
		Vector<Vehicle> vehicles = null;
		try {
			vehicles = vehicleDataService.getListBySql(sql);
			for (Vehicle vehicle : vehicles) {
				vehicle.setDepartment(departmentDataService.getById(vehicle.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicles;
	}

}
