package nju.express.blservice.impl;

import nju.express.blservice.UserblService;
import nju.express.dataservice.UserDataService;
import nju.express.vo.User;

public class UserblServiceImpl implements UserblService {
	private UserDataService userDataService;

	public UserblServiceImpl(UserDataService userDataService) {
		this.userDataService = userDataService;
	}

	@Override
	public void login(String name, String password) {
		User user = userDataService.getUser(name, password);
		if (user == null) {
			System.out.println("”√ªß√˚√‹¬Î¥ÌŒÛ");
		}
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(int id, User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
