package nju.express;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import nju.express.dataservice.CollectionDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.GoodsDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.PostDataService;
import nju.express.dataservice.ReceiverDataService;
import nju.express.dataservice.SenderDataService;
import nju.express.dataservice.UserDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.dataservice.impl.CollectionDataServiceImpl;
import nju.express.dataservice.impl.DepartmentDataServiceImpl;
import nju.express.dataservice.impl.GoodsDataServiceImpl;
import nju.express.dataservice.impl.JobDataServiceImpl;
import nju.express.dataservice.impl.PostDataServiceImpl;
import nju.express.dataservice.impl.ReceiverDataServiceImpl;
import nju.express.dataservice.impl.SenderDataServiceImpl;
import nju.express.dataservice.impl.UserDataServiceImpl;
import nju.express.dataservice.impl.VehicleDataServiceImpl;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			UserDataService userDataService = new UserDataServiceImpl();

			PostDataService postDataService = new PostDataServiceImpl();
			SenderDataService senderDataService = new SenderDataServiceImpl();
			ReceiverDataService receiverDataService = new ReceiverDataServiceImpl();
			GoodsDataService goodsDataService = new GoodsDataServiceImpl();
			CollectionDataService collectionDataService = new CollectionDataServiceImpl();

			JobDataService jobDataService = new JobDataServiceImpl();
			DepartmentDataService departmentDataService = new DepartmentDataServiceImpl();

			VehicleDataService vehicleDataService = new VehicleDataServiceImpl();

			LocateRegistry.createRegistry(6600);
			Naming.rebind("rmi://127.0.0.1:6600/userDataService", userDataService);
			Naming.rebind("rmi://127.0.0.1:6600/postDataService", postDataService);
			Naming.rebind("rmi://127.0.0.1:6600/senderDataService", senderDataService);
			Naming.rebind("rmi://127.0.0.1:6600/receiverDataService", receiverDataService);
			Naming.rebind("rmi://127.0.0.1:6600/goodsDataService", goodsDataService);
			Naming.rebind("rmi://127.0.0.1:6600/collectionDataService", collectionDataService);
			Naming.rebind("rmi://127.0.0.1:6600/jobDataService", jobDataService);
			Naming.rebind("rmi://127.0.0.1:6600/departmentDataService", departmentDataService);
			Naming.rebind("rmi://127.0.0.1:6600/vehicleDataService", vehicleDataService);

			logger.info("service start");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
