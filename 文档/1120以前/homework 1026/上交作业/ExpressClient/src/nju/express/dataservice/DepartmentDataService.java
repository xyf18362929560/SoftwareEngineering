package nju.express.dataservice;

import nju.express.vo.Department;

public interface DepartmentDataService {
	void addDepartment(Department department);

	void deleteDepartment(int id);

	void updataDepartment(int id, Department department);

	Department getDepartment(int id);
}
