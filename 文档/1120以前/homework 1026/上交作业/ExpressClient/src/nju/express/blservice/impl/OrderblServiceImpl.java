package nju.express.blservice.impl;


import nju.express.blservice.OrderblService;
import nju.express.vo.BItransport;
import nju.express.vo.Collection;
import nju.express.vo.Delivery;
import nju.express.vo.IBtransport;
import nju.express.vo.IItransport;
import nju.express.vo.Order;
import nju.express.vo.Post;
import nju.express.vo.Recipient;

public class OrderblServiceImpl implements OrderblService{

	@Override
	public void createOrder(String barcode, Post post, Collection collection,BItransport bItransport,
			IItransport iItransport, IBtransport iBtransport, int startStock,
			int endStrock, Delivery delivery, Recipient recipient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order checkOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeOrder(int id, String barcode, Post post,
			Collection collection,BItransport bItransport, IItransport iItransport,
			IBtransport iBtransport, int startStock, int endStrock,
			Delivery delivery, Recipient recipient) {
		// TODO Auto-generated method stub
		
	}
	
}
