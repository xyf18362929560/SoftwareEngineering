package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Job;

public interface JobDataService extends Remote{
    Job find(int id) throws RemoteException;
}
