package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Vehicleblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.po.Department;
import nju.express.po.Vehicle;
import nju.express.util.LoggerUtil;

public class VehicleblserviceImpl implements Vehicleblservice {
	static Logger logger = LoggerUtil.getLogger();
	private VehicleDataService vehicleDataService;
	private DepartmentDataService departmentDataService;

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
			logger.error(e.getMessage() + "获得车辆信息失败");
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
			logger.error(e.getMessage() + "获得车辆信息失败");
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
			logger.error(e.getMessage() + "新增车辆信息失败");
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
			logger.error(e.getMessage() + "更新车辆信息失败");
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
			logger.error(e.getMessage() + "删除车辆信息失败");
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
			logger.error(e.getMessage() + "获得营业厅信息失败");
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
			logger.error(e.getMessage() + "获得车辆信息失败");
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
			logger.error(e.getMessage() + "获得车辆信息失败");
			e.printStackTrace();
		}
		return vehicles;
	}

	public VehicleDataService getVehicleDataService() {
		return vehicleDataService;
	}

	public void setVehicleDataService(VehicleDataService vehicleDataService) {
		this.vehicleDataService = vehicleDataService;
	}

	public DepartmentDataService getDepartmentDataService() {
		return departmentDataService;
	}

	public void setDepartmentDataService(DepartmentDataService departmentDataService) {
		this.departmentDataService = departmentDataService;
	}

}
