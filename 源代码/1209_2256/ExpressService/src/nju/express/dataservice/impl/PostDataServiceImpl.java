package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.PostDataService;
import nju.express.po.Post;
import nju.express.util.DAO;

public class PostDataServiceImpl extends UnicastRemoteObject implements PostDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 942760528702977548L;

	public PostDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public Vector<Post> getList() throws RemoteException {
		Vector<Post> posts = DAO.getList(Post.class);
		return posts;
	}

	@Override
	public Post getById(int id) throws RemoteException {
		Post post = (Post) DAO.getObById(Post.class, id);
		return post;
	}

	@Override
	public int insert(Post post) throws RemoteException {
		String sql = "insert into post ( barcode , sender_id_fk , receiver_id_fk , goods_id_fk , user_id_fk , post_type , packingexpense , collectionfare , post_setupdatetime , post_estimateddatetime) "
				+ " values (?,?,?,?,?,?,?,?,?,?) ";
		Object[] obs = { post.getBarcode(), post.getSender_id_fk(), post.getReceiver_id_fk(), post.getGoods_id_fk(),
				post.getUser_id_fk(), post.getPost_type(), post.getPackingexpense(), post.getCollectionfare(),
				post.getPost_setupdatetime(), post.getPost_estimateddatetime() };
		return DAO.insertGetGeneratedKeyBySql(Post.class, sql, obs);
	}

	@Override
	public boolean update(Post post) throws RemoteException {
		String sql = "update post set barcode = ? , sender_id_fk = ? , receiver_id_fk = ? , goods_id_fk = ? ,user_id_fk = ? , post_type = ? , packingexpense = ? , collectionfare = ? , post_setupdatetime = ? , post_estimateddatetime = ? "
				+ "where id = ? ";
		Object[] obs = { post.getBarcode(), post.getSender_id_fk(), post.getReceiver_id_fk(), post.getGoods_id_fk(),
				post.getUser_id_fk(), post.getPost_type(), post.getPackingexpense(), post.getCollectionfare(),
				post.getPost_setupdatetime(), post.getPost_estimateddatetime(), post.getId() };
		return DAO.updateBySql(Post.class, sql, obs);
	}

	@Override
	public boolean update_fk_id(String sql, Object[] obs) throws RemoteException {
		return DAO.updateBySql(Post.class, sql, obs);
	}

	@Override
	public boolean delete(int id) throws RemoteException {

		return DAO.delete(Post.class, id);
	}

	@Override
	public Vector<Post> getListByLike(String columnname, Object value) throws RemoteException {
		Vector<Post> posts = DAO.getListByLike(Post.class, columnname, value);
		return posts;
	}

	@Override
	public Vector<Post> getListBySql(String sql) throws RemoteException {
		Vector<Post> posts = DAO.getListBySql(Post.class, sql);
		return posts;
	}

	@Override
	public Vector<Post> getListByCondition(String columnname, Object value) throws RemoteException {
		Vector<Post> posts = DAO.getListByCondition(Post.class, columnname, value);
		return posts;
	}

	@Override
	public Vector<Post> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Post.class, sql, obs);
	}

}
