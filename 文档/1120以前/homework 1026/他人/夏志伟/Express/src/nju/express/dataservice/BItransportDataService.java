package nju.express.dataservice;

import nju.express.vo.BItransport;

public interface BItransportDataService {
	void addBItransport(BItransport bitransport);

	void deleteBItransport(int id);

	void updateBItransport(int id, BItransport bitransport);

	BItransport getBItransport(int id);
}
