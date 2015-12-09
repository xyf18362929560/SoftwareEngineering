package nju.express.dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.express.vo.Post;

public interface PostDataService extends Remote{
    void insert(Post post) throws RemoteException;
    Post find(int id) throws RemoteException;
}
