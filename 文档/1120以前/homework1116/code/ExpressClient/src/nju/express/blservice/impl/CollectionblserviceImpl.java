package nju.express.blservice.impl;

import java.rmi.RemoteException;
import java.util.Vector;

import nju.express.blservice.Collectionblservice;
import nju.express.dataservice.CollectionDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.UserDataService;
import nju.express.vo.Collection;

public class CollectionblserviceImpl implements Collectionblservice{

	private CollectionDataService collectionDataService;
	private UserDataService userDataService;
	private DepartmentDataService departmentDataService;
	
	
	

	public CollectionblserviceImpl(CollectionDataService collectionDataService, UserDataService userDataService,
			DepartmentDataService departmentDataService) {
		super();
		this.collectionDataService = collectionDataService;
		this.userDataService = userDataService;
		this.departmentDataService = departmentDataService;
	}

	@Override
	public Vector<Collection> getAll() {
		Vector<Collection> collections=null;
		try {
			collections=collectionDataService.getAll();
			for(Collection collection: collections){
				collection.setCourier_user(userDataService.getById(collection.getCourier_user_id_fk()));
				collection.setDepartment(departmentDataService.getById(collection.getDepartment_id_fk()));
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
			collection.setDepartment(departmentDataService.getById(collection.getDepartment_id_fk()));
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
