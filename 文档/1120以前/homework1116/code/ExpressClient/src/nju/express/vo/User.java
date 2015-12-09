package nju.express.vo;

import java.io.Serializable;

public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4657886619226966931L;
	private int id;
	private String user_name;
	private String user_password;
	private int job_id_fk;
	private int department_id_fk;
	private String user_info;
	
	//外键需要的实体类的属性
	private Job job;
	private Department department;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public int getJob_id_fk() {
		return job_id_fk;
	}
	public void setJob_id_fk(int job_id_fk) {
		this.job_id_fk = job_id_fk;
	}
	public int getDepartment_id_fk() {
		return department_id_fk;
	}
	public void setDepartment_id_fk(int department_id_fk) {
		this.department_id_fk = department_id_fk;
	}
	public String getUser_info() {
		return user_info;
	}
	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	
	
}
