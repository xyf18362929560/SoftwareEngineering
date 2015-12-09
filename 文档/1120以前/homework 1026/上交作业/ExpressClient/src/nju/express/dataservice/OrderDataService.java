package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.BItransport;
import nju.express.vo.Collection;
import nju.express.vo.Delivery;
import nju.express.vo.IBtransport;
import nju.express.vo.IItransport;
import nju.express.vo.Order;
import nju.express.vo.Post;
import nju.express.vo.Recipient;

public interface OrderDataService extends Remote{
    void insert(Order order) throws RemoteException;
    Order find(int id) throws RemoteException;
    void change(int id,Order order) throws RemoteException;
}
