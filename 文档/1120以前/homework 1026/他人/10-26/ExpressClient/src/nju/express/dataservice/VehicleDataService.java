package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Vehicle;

public interface VehicleDataService extends Remote{
    Vehicle getVehicle(int id) throws RemoteException;
    Vehicle getVehicle(String lience) throws RemoteException;
}
