package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.express.dataservice.PostDataService;
import nju.express.vo.Post;

public class PostDataServiceImpl extends UnicastRemoteObject implements PostDataService{

	protected PostDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Post post) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post find(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
