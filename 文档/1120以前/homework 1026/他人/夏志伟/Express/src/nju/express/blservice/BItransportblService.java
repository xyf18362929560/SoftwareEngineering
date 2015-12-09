package nju.express.blservice;

import nju.express.vo.BItransport;

public interface BItransportblService {
	void addBItransport(BItransport bitransport);
	
	void deleteBItransport(int id);
	
	void updateBItransport(int id, BItransport bitransport);
	
	BItransport getBItransport(int id);
}
