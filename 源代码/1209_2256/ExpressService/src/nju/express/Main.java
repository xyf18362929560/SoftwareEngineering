package nju.express;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import nju.express.dataservice.AccountDataService;
import nju.express.dataservice.BItransportDataService;
import nju.express.dataservice.DeliveryDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.DriverDataService;
import nju.express.dataservice.FareDataService;
import nju.express.dataservice.GoodsDataService;
import nju.express.dataservice.IItransportDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.PaymentDataService;
import nju.express.dataservice.PostDataService;
import nju.express.dataservice.ReceiverDataService;
import nju.express.dataservice.RecipientDataService;
import nju.express.dataservice.SenderDataService;
import nju.express.dataservice.StockDataService;
import nju.express.dataservice.UserDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.dataservice.impl.AccountDataServiceImpl;
import nju.express.dataservice.impl.BItransportDataServiceImpl;
import nju.express.dataservice.impl.DeliveryDataServiceImpl;
import nju.express.dataservice.impl.DepartmentDataServiceImpl;
import nju.express.dataservice.impl.DriverDataServiceImpl;
import nju.express.dataservice.impl.FareDataServiceImpl;
import nju.express.dataservice.impl.GoodsDataServiceImpl;
import nju.express.dataservice.impl.IItransportDataServiceImpl;
import nju.express.dataservice.impl.JobDataServiceImpl;
import nju.express.dataservice.impl.PaymentDataServiceImpl;
import nju.express.dataservice.impl.PostDataServiceImpl;
import nju.express.dataservice.impl.ReceiverDataServiceImpl;
import nju.express.dataservice.impl.RecipientDataServiceImpl;
import nju.express.dataservice.impl.SenderDataServiceImpl;
import nju.express.dataservice.impl.StockDataServiceImpl;
import nju.express.dataservice.impl.UserDataServiceImpl;
import nju.express.dataservice.impl.VehicleDataServiceImpl;
import nju.express.util.LoggerUtil;

public class Main {
	static Logger logger = LoggerUtil.getLogger();

	public static void main(String[] args) {
		try {
			UserDataService userDataService = new UserDataServiceImpl();

			PostDataService postDataService = new PostDataServiceImpl();
			SenderDataService senderDataService = new SenderDataServiceImpl();
			ReceiverDataService receiverDataService = new ReceiverDataServiceImpl();
			GoodsDataService goodsDataService = new GoodsDataServiceImpl();

			JobDataService jobDataService = new JobDataServiceImpl();
			DepartmentDataService departmentDataService = new DepartmentDataServiceImpl();

			VehicleDataService vehicleDataService = new VehicleDataServiceImpl();
			DriverDataService driverDataService = new DriverDataServiceImpl();
			BItransportDataService bItransportDataService = new BItransportDataServiceImpl();

			StockDataService stockDataService = new StockDataServiceImpl();
			IItransportDataService iItransportDataService = new IItransportDataServiceImpl();

			DeliveryDataService deliveryDataService=new DeliveryDataServiceImpl();
			RecipientDataService recipientDataService =new RecipientDataServiceImpl();
			
			PaymentDataService paymentDataService=new PaymentDataServiceImpl();
			
			AccountDataService accountDataService=new AccountDataServiceImpl();
			
			FareDataService fareDataService=new FareDataServiceImpl();
			
			LocateRegistry.createRegistry(6600);
			Naming.rebind("rmi://127.0.0.1:6600/userDataService", userDataService);
			Naming.rebind("rmi://127.0.0.1:6600/postDataService", postDataService);
			Naming.rebind("rmi://127.0.0.1:6600/senderDataService", senderDataService);
			Naming.rebind("rmi://127.0.0.1:6600/receiverDataService", receiverDataService);
			Naming.rebind("rmi://127.0.0.1:6600/goodsDataService", goodsDataService);

			Naming.rebind("rmi://127.0.0.1:6600/jobDataService", jobDataService);
			Naming.rebind("rmi://127.0.0.1:6600/departmentDataService", departmentDataService);
			Naming.rebind("rmi://127.0.0.1:6600/vehicleDataService", vehicleDataService);
			Naming.rebind("rmi://127.0.0.1:6600/driverDataService", driverDataService);
			Naming.rebind("rmi://127.0.0.1:6600/bItransportDataService", bItransportDataService);
			Naming.rebind("rmi://127.0.0.1:6600/stockDataService", stockDataService);
			Naming.rebind("rmi://127.0.0.1:6600/iItransportDataService", iItransportDataService);
			Naming.rebind("rmi://127.0.0.1:6600/deliveryDataService", deliveryDataService);
			Naming.rebind("rmi://127.0.0.1:6600/recipientDataService", recipientDataService);
			Naming.rebind("rmi://127.0.0.1:6600/paymentDataService", paymentDataService);
			Naming.rebind("rmi://127.0.0.1:6600/accountDataService", accountDataService);
			Naming.rebind("rmi://127.0.0.1:6600/fareDataService", fareDataService);
			logger.info("服务器启动成功");
		} catch (Exception e) {
			logger.error(e.getMessage() + "服务器启动失败");
			e.printStackTrace();
		}
	}

}
