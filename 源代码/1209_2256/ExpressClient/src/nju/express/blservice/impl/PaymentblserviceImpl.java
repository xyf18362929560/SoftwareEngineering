package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Paymentblservice;
import nju.express.dataservice.PaymentDataService;
import nju.express.dataservice.UserDataService;
import nju.express.po.Driver;
import nju.express.po.Payment;
import nju.express.po.User;
import nju.express.util.LoggerUtil;

public class PaymentblserviceImpl implements Paymentblservice {
	static Logger logger = LoggerUtil.getLogger();
	PaymentDataService paymentDataService;
	UserDataService userDataService;

	public PaymentblserviceImpl(PaymentDataService paymentDataService, UserDataService userDataService) {
		this.paymentDataService = paymentDataService;
		this.userDataService = userDataService;
	}

	@Override
	public Vector<Payment> getAll() {
		Vector<Payment> payments = null;
		try {
			payments = paymentDataService.getAll();
			for (Payment payment : payments) {
				payment.setFinance_user(userDataService.getById(payment.getFinance_user_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得付款单信息失败");
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public Vector<Payment> getBySql(String sql, Object[] values) {
		Vector<Payment> payments = null;
		try {
			payments = paymentDataService.getBySql(sql, values);
			for (Payment payment : payments) {
				payment.setFinance_user(userDataService.getById(payment.getFinance_user_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得付款单信息失败");
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public Payment getById(int id) {
		Payment payment = null;
		try {
			payment = paymentDataService.getById(id);
			payment.setFinance_user(userDataService.getById(payment.getFinance_user_id_fk()));

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得付款单信息失败");
			e.printStackTrace();
		}
		return payment;
	}

	@Override
	public int add(Payment payment) {
		int result = 0;
		try {
			result = paymentDataService.insert(payment);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增付款单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Payment payment) {
		boolean result = false;
		try {
			result = paymentDataService.update(payment);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新付款单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = paymentDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除付款单信息失败");

		}
		return result;
	}

	

}
