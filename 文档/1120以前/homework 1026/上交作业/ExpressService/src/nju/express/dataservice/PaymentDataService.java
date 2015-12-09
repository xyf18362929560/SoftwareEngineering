package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Payment;

public interface PaymentDataService extends Remote{
    void insert(Payment payment) throws RemoteException;
    Payment find(int id) throws RemoteException;
    double statistic(String starttime,String endtime) throws RemoteException;
}
