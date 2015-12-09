package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Departmentblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.po.Department;
import nju.express.util.LoggerUtil;

public class DepartmentblserviceImpl implements Departmentblservice {
	static Logger logger = LoggerUtil.getLogger();
	DepartmentDataService departmentDataService;

	public DepartmentblserviceImpl(DepartmentDataService departmentDataService) {
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<Department> getAll() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getAll();
			
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得部门信息失败");
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public Vector<Department> getBySql(String sql, Object[] values) {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getBySql(sql, values);
			
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得部门信息失败");
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public Department getById(int id) {
		Department department = null;
		try {
			department = departmentDataService.getById(id);
			
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得部门信息失败");
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public int add(Department department) {
		int result = 0;
		try {
			result = departmentDataService.insert(department);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增部门信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Department department) {
		boolean result = false;
		try {
			result = departmentDataService.update(department);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新部门信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = departmentDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除部门信息失败");

		}
		return result;
	}

}
