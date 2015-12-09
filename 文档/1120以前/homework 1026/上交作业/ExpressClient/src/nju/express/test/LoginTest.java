package nju.express.test;

import java.rmi.Naming;

import nju.express.blservice.UserblService;
import nju.express.blservice.impl.UserblServiceImpl;
import nju.express.dataservice.UserDataService;

public class LoginTest {

	public static void main(String[] args)  {
		UserDataService userDataService;
		try {
			userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:6600/test1");
			UserblService userblService = new UserblServiceImpl(userDataService);
//			LoginFrame loginFrame = new LoginFrame(userblService);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
