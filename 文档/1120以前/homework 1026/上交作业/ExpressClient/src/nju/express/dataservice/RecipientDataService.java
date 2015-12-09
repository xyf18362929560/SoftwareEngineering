package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Recipient;

public interface RecipientDataService extends Remote{
    void insert(Recipient recipient) throws RemoteException;
    Recipient find(int id) throws RemoteException;
    Recipient find(String name) throws RemoteException;
}
