package nju.express.blservice.impl;

import java.util.List;

import nju.express.blservice.AccountblService;
import nju.express.vo.Collection;
import nju.express.vo.Payment;

public class AccountblServiceImpl implements AccountblService{

	@Override
	public List<Collection> checkCollection(int id, String account_name, String account_startdate,
			String account_enddate) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Payment> checkPayment(int id, String account_name, String account_startdate, String account_enddate) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public double checkamount(int id, String account_name, String account_startdate, String account_enddate) {
		// TODO Auto-generated method stub
		
		return 0;
	}

}
