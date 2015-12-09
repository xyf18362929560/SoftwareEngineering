package nju.express.blservice;

import nju.express.vo.BItransport;
import nju.express.vo.Collection;
import nju.express.vo.Delivery;
import nju.express.vo.IBtransport;
import nju.express.vo.IItransport;
import nju.express.vo.Order;
import nju.express.vo.Post;
import nju.express.vo.Recipient;

public interface OrderblService {
	void createOrder(String barcode,Post post,Collection collection,BItransport bItransport,IItransport iItransport,
			IBtransport iBtransport,int startStock,int endStrock,Delivery delivery,
			Recipient recipient);
	Order checkOrder(int id);
	void changeOrder(int id,String barcode,Post post,Collection collection,BItransport bItransport,IItransport iItransport,
			IBtransport iBtransport,int startStock,int endStrock,Delivery delivery,
			Recipient recipient);
}
