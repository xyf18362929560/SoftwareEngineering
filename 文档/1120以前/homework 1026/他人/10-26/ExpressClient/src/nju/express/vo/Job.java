package nju.express.vo;

public class Job {
	private int id;
	private String job_name;
	private double job_salary;
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public double getJob_salary() {
		return job_salary;
	}
	public void setJob_salary(double job_salary) {
		this.job_salary = job_salary;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
