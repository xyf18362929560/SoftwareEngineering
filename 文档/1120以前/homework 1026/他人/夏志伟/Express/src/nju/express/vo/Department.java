package nju.express.vo;

public class Department {
	public  static String FINANCE="���������";
	public static String BUSINESS="Ӫҵ��";
	public static String TRANSFER="��ת����";
	
	private int id;
	private String department_name;
	private String department_location;
	private String department_type;
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
	public String getDepartment_type() {
		return department_type;
	}
	public void setDepartment_type(String department_type) {
		this.department_type = department_type;
	}
}
