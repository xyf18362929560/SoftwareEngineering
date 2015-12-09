package nju.express.blservice;

import nju.express.vo.Delivery;

public interface DeliveryblService {
	void addDelivery(Delivery delivery);
	
	void deleteDelivery(int id);
	
	void updateDelivery(int id,Delivery delivery);
	
	Delivery getDelivery(int id);
}
