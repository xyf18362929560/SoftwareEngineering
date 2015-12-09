package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.IItransportblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.IItransportDataService;
import nju.express.po.Department;
import nju.express.po.IItransport;
import nju.express.util.LoggerUtil;

public class IItransportblserviceImpl implements IItransportblservice {

	static Logger logger = LoggerUtil.getLogger();
	IItransportDataService iItransportDataService;
	DepartmentDataService departmentDataService;

	public IItransportblserviceImpl(IItransportDataService iItransportDataService,
			DepartmentDataService departmentDataService) {
		this.iItransportDataService = iItransportDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<IItransport> getAll() {
		Vector<IItransport> iItransports = null;
		try {
			iItransports = iItransportDataService.getAll();
			for (IItransport iItransport : iItransports) {
				iItransport.setStart_department(departmentDataService.getById(iItransport.getStart_department_id_fk()));
				iItransport.setEnd_department(departmentDataService.getById(iItransport.getEnd_department_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得中转中心装车单信息失败");
			e.printStackTrace();
		}
		return iItransports;
	}

	@Override
	public Vector<IItransport> getBySql(String sql, Object[] values) {
		Vector<IItransport> iItransports = null;
		try {
			iItransports = iItransportDataService.getBySql(sql, values);
			for (IItransport iItransport : iItransports) {
				iItransport.setStart_department(departmentDataService.getById(iItransport.getStart_department_id_fk()));
				iItransport.setEnd_department(departmentDataService.getById(iItransport.getEnd_department_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得中转中心装车单信息失败");
			e.printStackTrace();
		}
		return iItransports;
	}

	@Override
	public IItransport getById(int id) {
		IItransport iItransport = null;
		try {
			iItransport = iItransportDataService.getById(id);

			iItransport.setStart_department(departmentDataService.getById(iItransport.getStart_department_id_fk()));
			iItransport.setEnd_department(departmentDataService.getById(iItransport.getEnd_department_id_fk()));

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得中转中心装车单信息失败");
			e.printStackTrace();
		}
		return iItransport;
	}

	@Override
	public int add(IItransport iItransport) {
		int result = 0;
		try {
			result = iItransportDataService.insert(iItransport);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增中转中心装车单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(IItransport iItransport) {
		boolean result = false;
		try {
			result = iItransportDataService.update(iItransport);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新中转中心装车单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = iItransportDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除中转中心装车单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<Department> getCenter() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getDepartmentOfType(2);
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得中转中心信息失败");
			e.printStackTrace();
		}
		return departments;
	}

}
