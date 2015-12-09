package nju.express.dataservice;

import nju.express.vo.IItransport;

public interface IItransportDataService {
	void addIItransport(IItransport iitransport);

	void deleteIItransport(int id);

	void updateIItransport(int id, IItransport iitransport);

	IItransport getIItransport(int id);
}
