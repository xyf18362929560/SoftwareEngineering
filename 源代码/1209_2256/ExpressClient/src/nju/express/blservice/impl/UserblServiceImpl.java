package nju.express.blservice.impl;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.UserblService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.UserDataService;
import nju.express.po.Department;
import nju.express.po.Job;
import nju.express.po.User;
import nju.express.util.LoggerUtil;

public class UserblServiceImpl implements UserblService {
	static Logger logger = LoggerUtil.getLogger();
	private UserDataService userDataService;
	private JobDataService jobDataService;
	private DepartmentDataService departmentDataService;

	public UserblServiceImpl(UserDataService userDataService, JobDataService jobDataService,
			DepartmentDataService departmentDataService) {

		this.userDataService = userDataService;
		this.jobDataService = jobDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public User login(String name, String password) {
		User user = null;
		try {
			user = userDataService.getUser(name, password);
			user.setJob(jobDataService.getById(user.getJob_id_fk()));
			user.setDepartment(departmentDataService.getById(user.getDepartment_id_fk()));
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获得用户信息失败");
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public Vector<User> getAll() {
		Vector<User> users = null;
		try {
			users = userDataService.getAll();
			for (User user : users) {
				user.setJob(jobDataService.getById(user.getJob_id_fk()));
				user.setDepartment(departmentDataService.getById(user.getDepartment_id_fk()));
			}

		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获得用户信息失败");
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public Vector<User> getBySql(String sql, Object[] values) {
		Vector<User> users = null;
		try {
			users = userDataService.getBySql(sql, values);
			for (User user : users) {
				user.setJob(jobDataService.getById(user.getJob_id_fk()));
				user.setDepartment(departmentDataService.getById(user.getDepartment_id_fk()));
			}

		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获得用户信息失败");
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public User getById(int id) {
		User user = null;
		try {
			user = userDataService.getById(id);

			user.setJob(jobDataService.getById(user.getJob_id_fk()));
			user.setDepartment(departmentDataService.getById(user.getDepartment_id_fk()));

		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获得用户信息失败");
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public int add(User user) {
		int result = 0;
		try {
			result = userDataService.insert(user);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增用户信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(User user) {
		boolean result = false;
		try {
			result = userDataService.update(user);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新用户信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = userDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除用户信息失败");

		}
		return result;
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
	public Vector<Department> getDepartment() {
		Vector<Department> departments = null;
		try {
			departments = departmentDataService.getAll();
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取部门信息失败");
		}
		return departments;
	}

}
