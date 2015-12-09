package nju.express.po;

import java.io.Serializable;

public class Department implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5858362161944668135L;

	private int id;
	private String department_name;
	private String department_location;
	private int department_type;
	public Department() {
	}
	
	
	public Department(String department_name, String department_location, int department_type) {
		this.department_name = department_name;
		this.department_location = department_location;
		this.department_type = department_type;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_location() {
		return department_location;
	}

	public void setDepartment_location(String department_location) {
		this.department_location = department_location;
	}

	public int getDepartment_type() {
		return department_type;
	}

	public void setDepartment_type(int department_type) {
		this.department_type = department_type;
	}
}
