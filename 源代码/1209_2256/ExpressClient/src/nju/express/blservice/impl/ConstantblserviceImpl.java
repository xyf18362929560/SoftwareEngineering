package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Constantblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.FareDataService;
import nju.express.dataservice.JobDataService;
import nju.express.po.Department;
import nju.express.po.Fare;
import nju.express.po.Job;
import nju.express.util.LoggerUtil;

public class ConstantblserviceImpl implements Constantblservice {
	static Logger logger = LoggerUtil.getLogger();
	private DepartmentDataService departmentDataService;
	private JobDataService jobDataService;
	private FareDataService fareDataService;

	public ConstantblserviceImpl(DepartmentDataService departmentDataService, JobDataService jobDataService,
			FareDataService fareDataService) {
		this.departmentDataService = departmentDataService;
		this.jobDataService = jobDataService;
		this.fareDataService = fareDataService;
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
	public Vector<Job> getJob() {
		Vector<Job> jobs = null;
		try {
			jobs = jobDataService.getAll();
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取工作信息失败");
		}
		return jobs;
	}

	@Override
	public Vector<Fare> getFare() {
		Vector<Fare> fares = null;
		try {
			fares = fareDataService.getAll();
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取运费信息失败");
		}
		return fares;
	}

	@Override
	public Vector<Fare> getFareBySql(String sql, Object[] values) {
		Vector<Fare> fares = null;
		try {
			fares = fareDataService.getBySql(sql, values);
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取运费信息失败");
		}
		return fares;
	}

	@Override
	public int addFare(Fare fare) {
		int result = 0;
		try {
			result = fareDataService.insert(fare);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增运费信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateFare(String sql, Object[] values) {
		boolean result = false;
		try {
			result = fareDataService.updateBySql(sql, values);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新运费信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateJobSalary(String sql, Object[] values) {
		boolean result = false;
		try {
			result = jobDataService.updateBySql(sql, values);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新工作信息失败");
			e.printStackTrace();
		}
		return result;
	}

}
