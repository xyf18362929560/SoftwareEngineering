package nju.express.blservice.impl;

import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Stockblservice;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.StockDataService;
import nju.express.po.Driver;
import nju.express.po.Stock;
import nju.express.util.LoggerUtil;

public class StockblserviceImpl implements Stockblservice {

	static Logger logger = LoggerUtil.getLogger();
	StockDataService stockDataService;
	DepartmentDataService departmentDataService;

	public StockblserviceImpl(StockDataService stockDataService, DepartmentDataService departmentDataService) {
		this.stockDataService = stockDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<Stock> getAll() {
		Vector<Stock> stocks = null;
		try {
			stocks = stockDataService.getAll();
			for (Stock stock : stocks) {
				stock.setDepartment(departmentDataService.getById(stock.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得库存信息失败");
			e.printStackTrace();
		}
		return stocks;
	}

	@Override
	public Vector<Stock> getBySql(String sql, Object[] values) {
		Vector<Stock> stocks = null;
		try {
			stocks = stockDataService.getBySql(sql, values);
			for (Stock stock : stocks) {
				stock.setDepartment(departmentDataService.getById(stock.getDepartment_id_fk()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + "获得库存信息失败");
			e.printStackTrace();
		}
		return stocks;
	}

	@Override
	public Stock getById(int id) {
		Stock stock = null;
		try {
			stock = stockDataService.getById(id);

			stock.setDepartment(departmentDataService.getById(stock.getDepartment_id_fk()));

		} catch (Exception e) {
			logger.error(e.getMessage() + "获得库存信息失败");
			e.printStackTrace();
		}
		return stock;
	}

	@Override
	public int add(Stock stock) {
		int result = 0;
		try {
			result = stockDataService.insert(stock);

		} catch (Exception e) {
			logger.error(e.getMessage() + "新增库存信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Stock stock) {
		boolean result = false;
		try {
			result = stockDataService.update(stock);

		} catch (Exception e) {
			logger.error(e.getMessage() + "更新库存信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = stockDataService.delete(id);

		} catch (Exception e) {
			logger.error(e.getMessage() + "删除库存信息失败");

		}
		return result;
	}

}
