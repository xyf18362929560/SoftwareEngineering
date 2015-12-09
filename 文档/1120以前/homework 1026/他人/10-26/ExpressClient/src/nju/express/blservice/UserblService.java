package nju.express.blservice;

import nju.express.vo.Job;

public interface UserblService {
	void logIn(String name, String password);
	void logOut();
	void register(String name,String password, int job, int department);
	void revise(String name,String password, int newpassword,int newjob, int newdepartment);
}
