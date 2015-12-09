package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Stock;

public interface StockDataService extends Remote{
    void insert(Stock stock) throws RemoteException;
    Stock search(String time1,String time2) throws RemoteException;
}
