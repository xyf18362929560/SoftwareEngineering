package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Sender;

public interface SenderDataService extends Remote{
    void insert(Sender sender) throws RemoteException;
    Sender find(int id) throws RemoteException;
}
