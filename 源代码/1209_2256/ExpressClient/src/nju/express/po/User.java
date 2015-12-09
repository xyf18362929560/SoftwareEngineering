package nju.express.po;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4657886619226966931L;
	private int id;
	private String user_name;
	private String user_password;
	private int job_id_fk;
	private int department_id_fk;
	

	// 外键需要的实体类的属性
	private Job job;
	private Department department;
	
	public User() {
	}
	

	public User(String user_name, String user_password, int job_id_fk, int department_id_fk) {
		this.user_name = user_name;
		this.user_password = user_password;
		this.job_id_fk = job_id_fk;
		this.department_id_fk = department_id_fk;
	}


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
