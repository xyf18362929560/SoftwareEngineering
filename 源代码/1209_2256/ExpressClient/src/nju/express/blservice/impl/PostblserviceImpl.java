package nju.express.blservice.impl;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.log4j.Logger;

import nju.express.blservice.Postblservice;
import nju.express.dataservice.BItransportDataService;
import nju.express.dataservice.DeliveryDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.DriverDataService;
import nju.express.dataservice.GoodsDataService;
import nju.express.dataservice.IItransportDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.PostDataService;
import nju.express.dataservice.ReceiverDataService;
import nju.express.dataservice.RecipientDataService;
import nju.express.dataservice.SenderDataService;
import nju.express.dataservice.StockDataService;
import nju.express.dataservice.UserDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.po.BItransport;
import nju.express.po.Delivery;
import nju.express.po.IItransport;
import nju.express.po.Post;
import nju.express.po.User;
import nju.express.util.LoggerUtil;

/**
 * 寄件单业务逻辑窗口
 * 
 * @author 徐亚帆
 * @time 2015年10月31日上午1:24:05 <br>
 *       需要 <br>
 *       postDataService 寄件单数据逻辑 <br>
 *       senderDataService 寄件人数据逻辑<br>
 *       receiverDataService 收件人数据逻辑<br>
 *       goodsDataService 货物数据逻辑<br>
 *       userDataService 职员数据逻辑<br>
 *       collectionDataService 收款单数据逻辑
 */
public class PostblserviceImpl implements Postblservice {
	static Logger logger = LoggerUtil.getLogger();
	PostDataService postDataService;
	SenderDataService senderDataService;
	ReceiverDataService receiverDataService;
	GoodsDataService goodsDataService;
	UserDataService userDataService;
	JobDataService jobDataService;
	DepartmentDataService departmentDataService;
	VehicleDataService vehicleDataService;
	DriverDataService driverDataService;
	StockDataService stockDataService;
	BItransportDataService bItransportDataService;
	IItransportDataService iItransportDataService;
	DeliveryDataService deliveryDataService;
	RecipientDataService recipientDataService;

	/**
	 * @param postDataService
	 *            寄件单数据逻辑
	 * @param senderDataService
	 *            寄件人数据逻辑
	 * @param receiverDataService
	 *            收件人数据逻辑
	 * @param goodsDataService
	 *            货物数据逻辑
	 * @param userDataService
	 *            职员数据逻辑
	 * @param collectionDataService
	 *            收款单数据逻辑
	 */
	public PostblserviceImpl(PostDataService postDataService, SenderDataService senderDataService,
			ReceiverDataService receiverDataService, GoodsDataService goodsDataService, UserDataService userDataService,
			JobDataService jobDataService, DepartmentDataService departmentDataService,
			VehicleDataService vehicleDataService, DriverDataService driverDataService,
			StockDataService stockDataService, BItransportDataService bItransportDataService,
			IItransportDataService iItransportDataService, DeliveryDataService deliveryDataService,
			RecipientDataService recipientDataService) {

		this.postDataService = postDataService;
		this.senderDataService = senderDataService;
		this.receiverDataService = receiverDataService;
		this.goodsDataService = goodsDataService;
		this.userDataService = userDataService;
		this.jobDataService = jobDataService;
		this.departmentDataService = departmentDataService;
		this.vehicleDataService = vehicleDataService;
		this.driverDataService = driverDataService;
		this.stockDataService = stockDataService;
		this.bItransportDataService = bItransportDataService;
		this.iItransportDataService = iItransportDataService;
		this.deliveryDataService = deliveryDataService;
		this.recipientDataService = recipientDataService;
	}

	@Override
	public Vector<Post> getAll() {
		Vector<Post> posts = null;
		try {
			posts = postDataService.getList();
			for (Post post : posts) {
				post.setSender(senderDataService.getById(post.getSender_id_fk()));
				post.setReceiver(receiverDataService.getById(post.getReceiver_id_fk()));
				post.setGoods(goodsDataService.getById(post.getGoods_id_fk()));
				post.setUser(userDataService.getById(post.getUser_id_fk()));
				post.getUser().setJob(jobDataService.getById(post.getUser().getJob_id_fk()));
				post.getUser().setDepartment(departmentDataService.getById(post.getUser().getDepartment_id_fk()));
				post =fk_id(post);
			}
			
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取寄件单信息失败");
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public Post getById(int id) {
		Post post = null;
		try {
			post = postDataService.getById(id);
			post.setSender(senderDataService.getById(post.getSender_id_fk()));
			post.setReceiver(receiverDataService.getById(post.getReceiver_id_fk()));
			post.setGoods(goodsDataService.getById(post.getGoods_id_fk()));
			post.setUser(userDataService.getById(post.getUser_id_fk()));
			post.getUser().setJob(jobDataService.getById(post.getUser().getJob_id_fk()));
			post.getUser().setDepartment(departmentDataService.getById(post.getUser().getDepartment_id_fk()));
			post =fk_id(post);
		} catch (RemoteException e) {

			logger.error(e.getMessage() + "获取寄件单信息失败");
			e.printStackTrace();
		}
		return post;
	}

	@Override
	public int add(Post post) {
		int id = 0;
		try {
			int sender_id_fk = senderDataService.insert(post.getSender());
			int receiver_id_fk = receiverDataService.insert(post.getReceiver());
			int goods_id_fk = goodsDataService.insert(post.getGoods());

			post.setSender_id_fk(sender_id_fk);
			post.setReceiver_id_fk(receiver_id_fk);
			post.setGoods_id_fk(goods_id_fk);

			id = postDataService.insert(post);

		} catch (RemoteException e) {

			logger.error(e.getMessage() + "新增寄件单信息失败");
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean update(Post post) {
		boolean result = false;
		try {
			senderDataService.update(post.getSender());
			receiverDataService.update(post.getReceiver());
			goodsDataService.update(post.getGoods());

			result = postDataService.update(post);

		} catch (RemoteException e) {
			logger.error(e.getMessage() + "更新寄件单信息失败");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update_fk_id(String sql, Object[] obs) {
		boolean result = false;
		try {
			result = postDataService.update_fk_id(sql, obs);

		} catch (RemoteException e) {
			logger.error(e.getMessage() + "更新寄件单信息失败");
			e.printStackTrace();
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
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取快递员信息失败");
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Vector<Post> getListByLike(String columnname, Object value) {
		Vector<Post> posts = null;
		try {
			posts = postDataService.getListByLike(columnname, value);
			for (Post post : posts) {
				post.setSender(senderDataService.getById(post.getSender_id_fk()));
				post.setReceiver(receiverDataService.getById(post.getReceiver_id_fk()));
				post.setGoods(goodsDataService.getById(post.getGoods_id_fk()));
				post.setUser(userDataService.getById(post.getUser_id_fk()));
				post.getUser().setJob(jobDataService.getById(post.getUser().getJob_id_fk()));
				post.getUser().setDepartment(departmentDataService.getById(post.getUser().getDepartment_id_fk()));
				post =fk_id(post);
			}
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取寄件单信息失败");
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public Vector<Post> getListBySql(String sql) {
		Vector<Post> posts = null;
		try {
			posts = postDataService.getListBySql(sql);
			for (Post post : posts) {
				post.setSender(senderDataService.getById(post.getSender_id_fk()));
				post.setReceiver(receiverDataService.getById(post.getReceiver_id_fk()));
				post.setGoods(goodsDataService.getById(post.getGoods_id_fk()));
				post.setUser(userDataService.getById(post.getUser_id_fk()));
				post.getUser().setJob(jobDataService.getById(post.getUser().getJob_id_fk()));
				post.getUser().setDepartment(departmentDataService.getById(post.getUser().getDepartment_id_fk()));
				post =fk_id(post);
			}
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取寄件单信息失败");
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public Vector<Post> getListByCondition(String columnname, Object value) {
		Vector<Post> posts = null;
		try {
			posts = postDataService.getListByCondition(columnname, value);
			for (Post post : posts) {
				post.setSender(senderDataService.getById(post.getSender_id_fk()));
				post.setReceiver(receiverDataService.getById(post.getReceiver_id_fk()));
				post.setGoods(goodsDataService.getById(post.getGoods_id_fk()));
				post.setUser(userDataService.getById(post.getUser_id_fk()));
				post.getUser().setJob(jobDataService.getById(post.getUser().getJob_id_fk()));
				post.getUser().setDepartment(departmentDataService.getById(post.getUser().getDepartment_id_fk()));
				post =fk_id(post);
			}
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取寄件单信息失败");
			e.printStackTrace();
		}
		return posts;
	}

	/**
	 * @param posts
	 *            基础的post
	 * @return 返回关联其他外键后的post
	 */
	@Override
	public Post fk_id(Post post) {

		try {

			
				if (post.getBItransport_id_fk() != 0) {

					post.setbItransport(bItransportDataService.getById(post.getBItransport_id_fk()));
					BItransport bItransport = post.getbItransport();
					bItransport.setStart_department(
							departmentDataService.getById(bItransport.getStart_department_id_fk()));
					bItransport.setEnd_department(departmentDataService.getById(bItransport.getEnd_department_id_fk()));
					bItransport.setVehicle(vehicleDataService.getById(bItransport.getVehicle_id_fk()));
					bItransport.setDriver(driverDataService.getById(bItransport.getDriver_id_fk()));
					bItransport.getVehicle().setDepartment(
							departmentDataService.getById(bItransport.getVehicle().getDepartment_id_fk()));
					bItransport.getDriver().setDepartment(
							departmentDataService.getById(bItransport.getDriver().getDepartment_id_fk()));
					post.setbItransport(bItransport);
				}
				if (post.getIBtransport_id_fk() != 0) {
					post.setiBtransport(bItransportDataService.getById(post.getIBtransport_id_fk()));
					BItransport iBtransport = post.getiBtransport();
					iBtransport.setStart_department(
							departmentDataService.getById(iBtransport.getStart_department_id_fk()));
					iBtransport.setEnd_department(departmentDataService.getById(iBtransport.getEnd_department_id_fk()));
					iBtransport.setVehicle(vehicleDataService.getById(iBtransport.getVehicle_id_fk()));
					iBtransport.setDriver(driverDataService.getById(iBtransport.getDriver_id_fk()));
					iBtransport.getVehicle().setDepartment(
							departmentDataService.getById(iBtransport.getVehicle().getDepartment_id_fk()));
					iBtransport.getDriver().setDepartment(
							departmentDataService.getById(iBtransport.getDriver().getDepartment_id_fk()));
					post.setiBtransport(iBtransport);

				}
				if (post.getIItransport_id_fk() != 0) {

					post.setiItransport(iItransportDataService.getById(post.getIItransport_id_fk()));
					IItransport iItransport = post.getiItransport();
					iItransport.setStart_department(
							departmentDataService.getById(iItransport.getStart_department_id_fk()));
					iItransport.setEnd_department(departmentDataService.getById(iItransport.getEnd_department_id_fk()));
					post.setiItransport(iItransport);
				}
				if (post.getStart_stock_id_fk() != 0) {

					post.setStart_stock(stockDataService.getById(post.getStart_stock_id_fk()));
					post.getStart_stock()
							.setDepartment(departmentDataService.getById(post.getStart_stock().getDepartment_id_fk()));
				}
				if (post.getEnd_stock_id_fk() != 0) {

					post.setEnd_stock(stockDataService.getById(post.getEnd_stock_id_fk()));
					post.getEnd_stock()
							.setDepartment(departmentDataService.getById(post.getEnd_stock().getDepartment_id_fk()));
				}

				if (post.getDelivery_id_fk() != 0) {
					post.setDelivery(deliveryDataService.getById(post.getDelivery_id_fk()));
					Delivery delivery = post.getDelivery();
					delivery.setCourier(userDataService.getById(delivery.getCourier_user_id_fk()));
					delivery.getCourier().setJob(jobDataService.getById(delivery.getCourier().getJob_id_fk()));
					delivery.getCourier().setDepartment(departmentDataService.getById(delivery.getCourier().getDepartment_id_fk()));
					post.setDelivery(delivery);
				}
				if (post.getRecipient_id_fk() != 0) {
					post.setRecipient(recipientDataService.getById(post.getRecipient_id_fk()));
				}
			
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取关联信息失败");
			e.printStackTrace();
		}
		return post;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			result = postDataService.delete(id);

		} catch (RemoteException e) {
			logger.error(e.getMessage() + "删除寄件单信息失败");
			e.printStackTrace();

		}
		return result;
	}

	@Override
	public Vector<Post> getBySql(String sql, Object[] values) {
		Vector<Post> posts = null;

		try {
			posts = postDataService.getBySql(sql, values);
			for (Post post : posts) {
				post.setSender(senderDataService.getById(post.getSender_id_fk()));
				post.setReceiver(receiverDataService.getById(post.getReceiver_id_fk()));
				post.setGoods(goodsDataService.getById(post.getGoods_id_fk()));
				post.setUser(userDataService.getById(post.getUser_id_fk()));
				post.getUser().setJob(jobDataService.getById(post.getUser().getJob_id_fk()));
				post.getUser().setDepartment(departmentDataService.getById(post.getUser().getDepartment_id_fk()));
				post =fk_id(post);
			}
		} catch (RemoteException e) {
			logger.error(e.getMessage() + "获取寄件单信息失败");
			e.printStackTrace();
		}
		return posts;
	}

}
