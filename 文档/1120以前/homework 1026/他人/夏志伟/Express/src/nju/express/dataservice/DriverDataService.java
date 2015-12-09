package nju.express.dataservice;

import nju.express.vo.Driver;

public interface DriverDataService {
	void addDriver(Driver driver);

	void deleteDriver(int id);

	void updateDriver(int id, Driver driver);

	Driver getDriver(int id);
}
