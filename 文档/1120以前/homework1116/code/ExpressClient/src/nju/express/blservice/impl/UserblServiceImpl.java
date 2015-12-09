package nju.express.blservice.impl;

import java.rmi.RemoteException;

import nju.express.blservice.UserblService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.UserDataService;
import nju.express.vo.User;

public class UserblServiceImpl implements UserblService {
	private UserDataService userDataService;
	private JobDataService jobDataService;
	private DepartmentDataService departmentDataService;
	

	public UserblServiceImpl(UserDataService userDataService, JobDataService jobDataService,
			DepartmentDataService departmentDataService) {
		
		this.userDataService = userDataService;
		this.jobDataService = jobDataService;
		this.departmentDataService = departmentDataService;
	}


	@Override
	public User login(String name, String password) {
		User user = null;
		try {
			user = userDataService.getUser(name, password);
			user.setJob(jobDataService.getById(user.getJob_id_fk()));
			user.setDepartment(departmentDataService.getById(user.getDepartment_id_fk()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
