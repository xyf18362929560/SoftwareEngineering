package nju.express.blservice.impl;

import java.rmi.RemoteException;

import nju.express.blservice.UserblService;
import nju.express.dataservice.UserDataService;
import nju.express.vo.Job;
import nju.express.vo.User;

public class UserblServiceImpl implements UserblService {
	private UserDataService userDataService;

	public UserblServiceImpl(UserDataService userDataService) {
		this.userDataService = userDataService;
	}

	@Override
	public void logIn(String name, String password) {
		User user = null;
		try {
			user = userDataService.getUser(name, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null) {
			System.out.println("”√ªß√˚√‹¬Î¥ÌŒÛ");
		}
	}

	public void logOut() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void register(String name, String password, int job, int department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revise(String name, String password, int newpassword,
			int newjob, int newdepartment) {
		// TODO Auto-generated method stub
		
	}


}
