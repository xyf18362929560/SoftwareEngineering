package nju.express.dataservice;

import nju.express.vo.IBtransport;

public interface IBtransportDataService {
	void addIBtransport(IBtransport ibtransport);

	void deleteIBtransport(int id);

	void updateIBtransport(int id, IBtransport ibtransport);

	IBtransport getIBtransport(int id);
}
