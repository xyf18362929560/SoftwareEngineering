package nju.express.blservice;

import nju.express.vo.Vehicle;

public interface VehicleblService {
	void createVehicle(String licence);
	void changeVehicle(int id,String newlicence);
	Vehicle getVehicle(int id);
	Vehicle getVehicle(String lience);
}
