package nju.express.blservice.impl;

import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.blservice.Collectionblservice;
import nju.express.dataservice.CollectionDataService;
import nju.express.dataservice.UserDataService;
import nju.express.po.Collection;

public class CollectionblserviceImpl implements Collectionblservice{

	private CollectionDataService collectionDataService;
	private UserDataService userDataService;
	
	
	
	
	public CollectionblserviceImpl(CollectionDataService collectionDataService, UserDataService userDataService) {
		
		this.collectionDataService = collectionDataService;
		this.userDataService = userDataService;
	}

	@Override
	public Vector<Collection> getAll() {
		Vector<Collection> collections=null;
		try {
			collections=collectionDataService.getAll();
			for(Collection collection: collections){
				collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collections;
	}

	@Override
	public Collection getByid(int id) {
		try {
			Collection collection=collectionDataService.getById(id);
			collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection add(Collection collection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection update(Collection collection) {
		// TODO Auto-generated method stub
		return null;
	}

}
