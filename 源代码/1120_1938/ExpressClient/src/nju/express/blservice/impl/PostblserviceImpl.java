package nju.express.blservice.impl;

import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.blservice.Postblservice;
import nju.express.dataservice.CollectionDataService;
import nju.express.dataservice.GoodsDataService;
import nju.express.dataservice.PostDataService;
import nju.express.dataservice.ReceiverDataService;
import nju.express.dataservice.SenderDataService;
import nju.express.dataservice.UserDataService;
import nju.express.po.Collection;
import nju.express.po.Post;
import nju.express.po.User;

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
	private PostDataService postDataService;
	private SenderDataService senderDataService;
	private ReceiverDataService receiverDataService;
	private GoodsDataService goodsDataService;
	private UserDataService userDataService;
	private CollectionDataService collectionDataService;

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
			CollectionDataService collectionDataService) {

		this.postDataService = postDataService;
		this.senderDataService = senderDataService;
		this.receiverDataService = receiverDataService;
		this.goodsDataService = goodsDataService;
		this.userDataService = userDataService;
		this.collectionDataService = collectionDataService;

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
				post.setCollection(collectionDataService.getById(post.getCollection_id_fk()));
				Collection collection = post.getCollection();
				collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
			}
		} catch (RemoteException e) {

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
			post.setCollection(collectionDataService.getById(post.getCollection_id_fk()));
			Collection collection = post.getCollection();
			collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return post;
	}

	@Override
	public int add(Post post) {
		int id=0;
		try {
			int sender_id_fk = senderDataService.insert(post.getSender());
			int receiver_id_fk = receiverDataService.insert(post.getReceiver());
			int goods_id_fk = goodsDataService.insert(post.getGoods());
			int collection_id_fk = collectionDataService.insert(post.getCollection());
			post.setSender_id_fk(sender_id_fk);
			post.setReceiver_id_fk(receiver_id_fk);
			post.setGoods_id_fk(goods_id_fk);
			post.setCollection_id_fk(collection_id_fk);
			id = postDataService.insert(post);
			
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean update(Post post) {
		boolean result=false;
		try {
			senderDataService.update(post.getSender());
			receiverDataService.update(post.getReceiver());
			goodsDataService.update(post.getGoods());
			collectionDataService.update(post.getCollection());

			result= postDataService.update(post);
			
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<User> getCourier() {
		Vector<User> users = null;
		try {
			users = userDataService.getUserOfJob(2);
		} catch (RemoteException e) {

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
				post.setCollection(collectionDataService.getById(post.getCollection_id_fk()));
				Collection collection = post.getCollection();
				collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
			}
		} catch (RemoteException e) {

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
				post.setCollection(collectionDataService.getById(post.getCollection_id_fk()));
				Collection collection = post.getCollection();
				collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
			}
		} catch (RemoteException e) {

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
				post.setCollection(collectionDataService.getById(post.getCollection_id_fk()));
				Collection collection = post.getCollection();
				collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
			}
		} catch (RemoteException e) {

			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public boolean delete(int id) {
		boolean result=false;
		try {
			result=postDataService.delete(id);

		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

}
