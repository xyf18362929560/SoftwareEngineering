package nju.express.dataservice.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import nju.express.dataservice.DepartmentDataService;
import nju.express.po.Department;
import nju.express.util.DAO;

public class DepartmentDataServiceImpl extends UnicastRemoteObject implements DepartmentDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -777643070024484060L;

	public DepartmentDataServiceImpl() throws RemoteException {
		super();

	}

	@Override
	public Vector<Department> getAll() throws RemoteException {
		return DAO.getList(Department.class);
	}

	@Override
	public Department getById(int id) throws RemoteException {
		return (Department) DAO.getObById(Department.class, id);
	}

	@Override
	public int insert(Department department) throws RemoteException {
		return DAO.insertGetGeneratedKey(department);
	}

	@Override
	public boolean update(Department department) throws RemoteException {

		return DAO.update(department, department.getId());
	}

	@Override
	public boolean delete(int id) throws RemoteException {
		return DAO.delete(Department.class, id);
	}

	@Override
	public Vector<Department> getDepartmentOfType(int type) throws RemoteException {
		Vector<Department> departments = DAO.getListByCondition(Department.class, "department_type", type);
		return departments;
	}

	@Override
	public Vector<Department> getBySql(String sql, Object[] obs) throws RemoteException {
		return DAO.getListBySql(Department.class, sql, obs);
	}

}
