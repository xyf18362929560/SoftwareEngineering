package nju.express.blservice;

import nju.express.vo.Driver;

public interface DriverblService {
	void addDriver(Driver driver);
	
	void deleteDriver(int id);
	
	void updateDriver(int id,Driver driver);
	
	Driver getDriver(int id);
}
