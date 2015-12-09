package nju.express.dataservice;

import nju.express.vo.Delivery;

public interface DeliveryDataService {
	void addDelivery(Delivery delivery);

	void deleteDelivery(int id);

	void updateDelivery(int id, Delivery delivery);

	Delivery getDelivery(int id);
}
