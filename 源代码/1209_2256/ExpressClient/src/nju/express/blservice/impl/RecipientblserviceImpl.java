package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Recipientblservice;
import nju.express.dataservice.RecipientDataService;
import nju.express.po.Recipient;
import nju.express.util.LoggerUtil;

public class RecipientblserviceImpl implements Recipientblservice {
	static Logger logger = LoggerUtil.getLogger();
	RecipientDataService recipientDataService;

	public RecipientblserviceImpl(RecipientDataService recipientDataService) {
		this.recipientDataService = recipientDataService;
	}

	@Override
	public Vector<Recipient> getAll() {
		Vector<Recipient> recipients = null;
		try {
			recipients = recipientDataService.getAll();

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得收件单信息失败");
			e.printStackTrace();
		}
		return recipients;
	}

	@Override
	public Vector<Recipient> getBySql(String sql, Object[] values) {
		Vector<Recipient> recipients = null;
		try {
			recipients = recipientDataService.getBySql(sql, values);

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得收件单信息失败");
			e.printStackTrace();
		}
		return recipients;
	}

	@Override
	public Recipient getById(int id) {
		Recipient recipient = null;
		try {
			recipient = recipientDataService.getById(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得收件单信息失败");
			e.printStackTrace();
		}
		return recipient;
	}

	@Override
	public int add(Recipient recipient) {
		int result = 0;
		try {
			result = recipientDataService.insert(recipient);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增收件单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Recipient recipient) {
		boolean result = false;
		try {
			result = recipientDataService.update(recipient);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新收件单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = recipientDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除收件单信息失败");

		}
		return result;
	}

}
