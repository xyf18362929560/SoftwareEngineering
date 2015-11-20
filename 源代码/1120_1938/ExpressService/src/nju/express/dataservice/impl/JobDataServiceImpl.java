package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.JobDataService;
import nju.express.po.Job;
import nju.express.util.DAO;

public class JobDataServiceImpl extends UnicastRemoteObject implements JobDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3238752547903284637L;

	public JobDataServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector<Job> getAll() throws RemoteException {

		return DAO.getList(Job.class);
	}

	@Override
	public Job getById(int id) throws RemoteException {
		return (Job) DAO.getObById(Job.class, id);
	}

	@Override
	public int insert(Job job) throws RemoteException {
		return DAO.insertGetGeneratedKey(job);
	}

	@Override
	public boolean update(Job job) throws RemoteException {
		
		return DAO.update(job, job.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Job.class, id);
	}

}
