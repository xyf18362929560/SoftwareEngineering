package nju.express.dataservice;

import java.util.List;

import nju.express.vo.Account;
import nju.express.vo.Collection;
import nju.express.vo.Payment;

public interface AccountDataService {
	List<Collection> checkCollection(int id, String account_name, String account_startdate, String account_enddate);

	List<Payment> checkPayment(int id, String account_name, String account_startdate, String account_enddate);

	double checkamount(int id, String account_name, String account_startdate, String account_enddate);
}
