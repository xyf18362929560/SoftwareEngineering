package nju.express.blservice.impl;

import java.rmi.RemoteException;

import nju.express.blservice.UserblService;
import nju.express.dataservice.UserDataService;
import nju.express.vo.User;

public class UserblServiceImpl implements UserblService {
	private UserDataService userDataService;

	public UserblServiceImpl(UserDataService userDataService) {
		this.userDataService = userDataService;
	}

	@Override
	public User login(String name, String password) {
		User user = null;
		try {
			user = userDataService.getUser(name, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
