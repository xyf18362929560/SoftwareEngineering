package nju.express.blservice;

import nju.express.vo.IItransport;

public interface IItransportblService {
	void addIItransport(IItransport iitransport);
	
	void deleteIItransport(int id);
	
	void updateIItransport(int id, IItransport iitransport);
	
	IItransport getIItransport(int id);
}