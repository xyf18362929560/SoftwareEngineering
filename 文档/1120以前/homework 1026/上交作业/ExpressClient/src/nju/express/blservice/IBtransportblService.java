package nju.express.blservice;

import nju.express.vo.IBtransport;

public interface IBtransportblService {
	void addIBtransport(IBtransport ibtransport);
	
	void deleteIBtransport(int id);
	
	void updateIBtransport(int id, IBtransport ibtransport);
	
	IBtransport getIBtransport(int id);
}
