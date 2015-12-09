package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Deliveryblservice;
import nju.express.dataservice.DeliveryDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.UserDataService;
import nju.express.po.Delivery;
import nju.express.po.User;
import nju.express.util.LoggerUtil;

public class DeliveryblserviceImpl implements Deliveryblservice {
	static Logger logger = LoggerUtil.getLogger();
	DeliveryDataService deliveryDataService;
	UserDataService userDataService;
	JobDataService jobDataService;
	DepartmentDataService departmentDataService;

	public DeliveryblserviceImpl(DeliveryDataService deliveryDataService, UserDataService userDataService,
			JobDataService jobDataService, DepartmentDataService departmentDataService) {
		this.deliveryDataService = deliveryDataService;
		this.userDataService = userDataService;
		this.jobDataService = jobDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<Delivery> getAll() {
		Vector<Delivery> deliveries = null;
		try {
			deliveries = deliveryDataService.getAll();
			for (Delivery delivery : deliveries) {
				delivery.setCourier(userDataService.getById(delivery.getCourier_user_id_fk()));
				delivery.getCourier().setJob(jobDataService.getById(delivery.getCourier().getJob_id_fk()));
				delivery.getCourier().setDepartment(departmentDataService.getById(delivery.getCourier().getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得派件单信息失败");
			e.printStackTrace();
		}
		return deliveries;
	}

	@Override
	public Vector<Delivery> getBySql(String sql, Object[] values) {
		Vector<Delivery> deliveries = null;
		try {
			deliveries = deliveryDataService.getBySql(sql, values);
			for (Delivery delivery : deliveries) {
				delivery.setCourier(userDataService.getById(delivery.getCourier_user_id_fk()));
				delivery.getCourier().setJob(jobDataService.getById(delivery.getCourier().getJob_id_fk()));
				delivery.getCourier().setDepartment(departmentDataService.getById(delivery.getCourier().getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得派件单信息失败");
			e.printStackTrace();
		}
		return deliveries;
	}

	@Override
	public Delivery getById(int id) {
		Delivery delivery = null;
		try {
			delivery = deliveryDataService.getById(id);

			delivery.setCourier(userDataService.getById(delivery.getCourier_user_id_fk()));
			delivery.getCourier().setJob(jobDataService.getById(delivery.getCourier().getJob_id_fk()));
			delivery.getCourier().setDepartment(departmentDataService.getById(delivery.getCourier().getDepartment_id_fk()));

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得派件单信息失败");
			e.printStackTrace();
		}
		return delivery;
	}

	@Override
	public int add(Delivery delivery) {
		int result = 0;
		try {
			result = deliveryDataService.insert(delivery);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增派件单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Delivery delivery) {
		boolean result = false;
		try {
			result = deliveryDataService.update(delivery);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新派件单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = deliveryDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除派件单信息失败");

		}
		return result;
	}

	@Override
	public Vector<User> getCourier(int department_id) {
		Vector<User> users = null;
		try {
			String sql = "select * from user where job_id_fk = ? and department_id_fk = ? ";
			Object[] values = { 2, department_id };
			users = userDataService.getBySql(sql, values);
			for (User user : users) {
				user.setJob(jobDataService.getById(user.getJob_id_fk()));
				user.setDepartment(departmentDataService.getById(user.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取快递员信息失败");
			e.printStackTrace();
		}
		return users;
	}

}
