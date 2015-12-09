package nju.express.test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import nju.express.dataservice.UserDataService;
import nju.express.dataservice.impl.UserDataServiceImpl;

public class RMIService {
	private static Logger logger = Logger.getLogger(RMIService.class);

	public static void main(String[] args) {
		try {
			UserDataService userDataService = new UserDataServiceImpl();
			LocateRegistry.createRegistry(6600);
			Naming.rebind("rmi://127.0.0.1:6600/test1", userDataService);

			logger.info("service start");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
