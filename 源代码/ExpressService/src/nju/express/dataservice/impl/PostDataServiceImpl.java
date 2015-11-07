package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.PostDataService;
import nju.express.po.Post;
import nju.express.util.DAO;

public class PostDataServiceImpl extends UnicastRemoteObject implements PostDataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 942760528702977548L;

	public PostDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<Post> getList() throws RemoteException {
		Vector<Post> posts =DAO.getList(Post.class);
		return posts;
	}

	@Override
	public Post getById(int id) throws RemoteException {
		Post post=(Post) DAO.getObById(Post.class, id);
		return post;
	}

	@Override
	public int insert(Post post) throws RemoteException {
		
		return DAO.insertGetGeneratedKey(post);
	}

	@Override
	public int update(Post post) throws RemoteException {
		DAO.update(post, post.getId());
		return post.getId();
	}


	@Override
	public boolean delete(int id) throws RemoteException {
		
		return DAO.delete(Post.class, id);
	}

	@Override
	public Vector<Post> getListByLike(String columnname, Object value) throws RemoteException {
		Vector<Post> posts=DAO.getListByLike(Post.class, columnname, value);
		return posts;
	}

	@Override
	public Vector<Post> getListBySql(String sql) throws RemoteException {
		Vector<Post> posts=DAO.getListBySql(Post.class, sql);
		return posts;
	}

	@Override
	public Vector<Post> getListByCondition(String columnname, Object value) throws RemoteException {
		Vector<Post> posts=DAO.getListByCondition(Post.class, columnname, value);
		return posts;
	}

	
	

}
