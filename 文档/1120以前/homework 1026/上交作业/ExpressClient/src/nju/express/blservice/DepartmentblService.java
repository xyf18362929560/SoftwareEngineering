package nju.express.blservice;

import nju.express.vo.Department;

public interface DepartmentblService {
	void addDepartment(Department department);
	
	void deleteDepartment(int id);
	
	void updataDepartment(int id, Department department);
	
	Department getDepartment(int id);
}
