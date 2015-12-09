package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Accountblservice;
import nju.express.dataservice.AccountDataService;
import nju.express.po.Account;
import nju.express.util.LoggerUtil;

public class AccountblserviceImpl implements Accountblservice {
	static Logger logger = LoggerUtil.getLogger();

	AccountDataService accountDataService;

	public AccountblserviceImpl(AccountDataService accountDataService) {
		this.accountDataService = accountDataService;
	}

	@Override
	public Vector<Account> getAll() {
		Vector<Account> accounts = null;
		try {
			accounts = accountDataService.getAll();

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得账户信息失败");
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Vector<Account> getBySql(String sql, Object[] values) {
		Vector<Account> accounts = null;
		try {
			accounts = accountDataService.getBySql(sql, values);

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得账户信息失败");
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account getById(int id) {
		Account account = null;
		try {
			account = accountDataService.getById(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得账户信息失败");
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public int add(Account account) {
		int result = 0;
		try {
			result = accountDataService.insert(account);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增账户信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Account account) {
		boolean result = false;
		try {
			result = accountDataService.update(account);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新账户信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = accountDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除账户信息失败");

		}
		return result;
	}

}
