package nju.express.test;

import nju.express.blservice.UserblService;
import nju.express.blservice.impl.UserblServiceImpl;
import nju.express.dataservice.UserDataService;
import nju.express.dataservice.impl.UserDataServiceImpl;
import nju.express.ui.LoginFrame;

public class LoginTest {

	public static void main(String[] args) {
		UserDataService userDataService=new UserDataServiceImpl();
		UserblService userblService=new UserblServiceImpl(userDataService);
		LoginFrame loginFrame=new LoginFrame(userblService);
	}

}
