package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Department;
import nju.express.po.IItransport;

public interface IItransportblservice {
	Vector<IItransport> getAll();

	Vector<IItransport> getBySql(String sql, Object[] values);

	IItransport getById(int id);

	int add(IItransport iItransport);

	boolean update(IItransport iItransport);

	boolean delete(int id);

	Vector<Department> getCenter();
}
