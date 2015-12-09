package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Driverblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.DriverDataService;
import nju.express.po.Department;
import nju.express.po.Driver;
import nju.express.util.LoggerUtil;

public class DriverblserviceImpl implements Driverblservice {
	static Logger logger = LoggerUtil.getLogger();
	DriverDataService driverDataService;
	DepartmentDataService departmentDataService;

	public DriverblserviceImpl(DriverDataService driverDataService, DepartmentDataService departmentDataService) {

		this.driverDataService = driverDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<Driver> getAll() {
		Vector<Driver> drivers = null;
		try {
			drivers = driverDataService.getAll();
			for (Driver driver : drivers) {
				driver.setDepartment(departmentDataService.getById(driver.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "���˾����Ϣʧ��");
			e.printStackTrace();
		}
		return drivers;
	}

	@Override
	public Driver getById(int id) {
		Driver driver = null;
		try {
			driver = driverDataService.getById(id);
			driver.setDepartment(departmentDataService.getById(driver.getDepartment_id_fk()));
		} catch (Exception e) {
			logger.error(e.getMessage() + "���˾����Ϣʧ��");
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public int add(Driver driver) {
		int result = 0;
		try {
			result = driverDataService.insert(driver);

		} catch (Exception e) {
			logger.error(e.getMessage() + "����˾����Ϣʧ��");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Driver driver) {
		boolean result = false;
		try {
			result = driverDataService.update(driver);

		} catch (Exception e) {
			logger.error(e.getMessage() + "����˾����Ϣʧ��");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = driverDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "ɾ��˾����Ϣʧ��");

		}
		return result;
	}

	@Override
	public Vector<Department> getBusiness() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getDepartmentOfType(1);
		} catch (Exception e) {
			logger.error(e.getMessage() + "��ȡӪҵ����Ϣʧ��");
		}
		return departments;
	}

	@Override
	public Vector<Driver> getBySql(String sql, Object[] values) {
		Vector<Driver> drivers = null;
		try {
			drivers = driverDataService.getBySql(sql, values);
			for (Driver driver : drivers) {
				driver.setDepartment(departmentDataService.getById(driver.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "��ȡ˾����Ϣʧ��");
			e.printStackTrace();
		}
		return drivers;
	}

}
