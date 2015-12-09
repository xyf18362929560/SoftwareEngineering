package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.BItransportblservice;
import nju.express.dataservice.BItransportDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.DriverDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.po.BItransport;
import nju.express.po.Department;
import nju.express.po.Driver;
import nju.express.po.Vehicle;
import nju.express.util.LoggerUtil;

public class BItransportblserviceImpl implements BItransportblservice {
	static Logger logger = LoggerUtil.getLogger();

	BItransportDataService bItransportDataService;
	DepartmentDataService departmentDataService;
	VehicleDataService vehicleDataService;
	DriverDataService driverDataService;

	public BItransportblserviceImpl(BItransportDataService bItransportDataService,
			DepartmentDataService departmentDataService, VehicleDataService vehicleDataService,
			DriverDataService driverDataService) {

		this.bItransportDataService = bItransportDataService;
		this.departmentDataService = departmentDataService;
		this.vehicleDataService = vehicleDataService;
		this.driverDataService = driverDataService;
	}

	@Override
	public Vector<BItransport> getAll() {
		Vector<BItransport> bItransports = null;
		try {
			bItransports = bItransportDataService.getAll();
			for (BItransport bItransport : bItransports) {
				bItransport.setStart_department(departmentDataService.getById(bItransport.getStart_department_id_fk()));
				bItransport.setEnd_department(departmentDataService.getById(bItransport.getEnd_department_id_fk()));
				bItransport.setVehicle(vehicleDataService.getById(bItransport.getVehicle_id_fk()));
				bItransport.setDriver(driverDataService.getById(bItransport.getDriver_id_fk()));
				bItransport.getVehicle()
						.setDepartment(departmentDataService.getById(bItransport.getVehicle().getDepartment_id_fk()));
				bItransport.getDriver()
						.setDepartment(departmentDataService.getById(bItransport.getDriver().getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得装车信息失败");
			e.printStackTrace();
		}
		return bItransports;
	}

	@Override
	public Vector<BItransport> getBySql(String sql, Object[] values) {
		Vector<BItransport> bItransports = null;
		try {
			bItransports = bItransportDataService.getBySql(sql, values);
			for (BItransport bItransport : bItransports) {
				bItransport.setStart_department(departmentDataService.getById(bItransport.getStart_department_id_fk()));
				bItransport.setEnd_department(departmentDataService.getById(bItransport.getEnd_department_id_fk()));
				bItransport.setVehicle(vehicleDataService.getById(bItransport.getVehicle_id_fk()));
				bItransport.setDriver(driverDataService.getById(bItransport.getDriver_id_fk()));
				bItransport.getVehicle()
						.setDepartment(departmentDataService.getById(bItransport.getVehicle().getDepartment_id_fk()));
				bItransport.getDriver()
						.setDepartment(departmentDataService.getById(bItransport.getDriver().getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得装车信息失败");
			e.printStackTrace();
		}
		return bItransports;
	}

	@Override
	public BItransport getById(int id) {
		BItransport bItransport = null;
		try {
			bItransport = bItransportDataService.getById(id);

			bItransport.setStart_department(departmentDataService.getById(bItransport.getStart_department_id_fk()));
			bItransport.setEnd_department(departmentDataService.getById(bItransport.getEnd_department_id_fk()));
			bItransport.setVehicle(vehicleDataService.getById(bItransport.getVehicle_id_fk()));
			bItransport.setDriver(driverDataService.getById(bItransport.getDriver_id_fk()));
			bItransport.getVehicle()
					.setDepartment(departmentDataService.getById(bItransport.getVehicle().getDepartment_id_fk()));
			bItransport.getDriver()
					.setDepartment(departmentDataService.getById(bItransport.getDriver().getDepartment_id_fk()));

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得装车信息失败");
			e.printStackTrace();
		}
		return bItransport;
	}

	@Override
	public int add(BItransport bItransport) {
		int result = 0;
		try {
			result = bItransportDataService.insert(bItransport);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增装车信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(BItransport bItransport) {
		boolean result = false;
		try {
			result = bItransportDataService.update(bItransport);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新装车信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = bItransportDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除装车信息失败");

		}
		return result;
	}

	@Override
	public Vector<Department> getBusiness() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getDepartmentOfType(1);
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取营业厅信息失败");
		}
		return departments;
	}

	@Override
	public Vector<Department> getCenter() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getDepartmentOfType(2);
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取中转中心信息失败");
		}
		return departments;
	}

	@Override
	public Vector<Vehicle> getVehicle(int department_id) {
		Vector<Vehicle> vehicles = null;
		try {
			vehicles = vehicleDataService.getByCondition("department_id_fk", department_id);
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取车辆信息失败");
		}
		return vehicles;
	}

	@Override
	public Vector<Driver> getDriver(int department_id) {
		Vector<Driver> drivers = null;
		try {
			String sql = "select * from driver where department_id_fk = ? ";
			Object[] obs = { department_id };
			drivers = driverDataService.getBySql(sql, obs);
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取司机信息失败");
		}
		return drivers;
	}

}
