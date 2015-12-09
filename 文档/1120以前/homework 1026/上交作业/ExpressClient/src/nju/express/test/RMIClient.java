package nju.express.test;

import java.rmi.Naming;

import nju.express.dataservice.UserDataService;
import nju.express.vo.User;

public class RMIClient {

	public static void main(String[] args) {
		try {
			UserDataService userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:6600/test1");
			User user = userDataService.getUser(1);
			System.out.println(user.getUser_name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
