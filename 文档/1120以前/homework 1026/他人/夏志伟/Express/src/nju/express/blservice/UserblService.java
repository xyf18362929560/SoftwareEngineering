package nju.express.blservice;

import nju.express.vo.User;

public interface UserblService {
	void login(String name, String password);
	
	void addUser(User user);
	
	void deleteUser(int id);
	
	void updateUser(int id, User user);
	
	User getUser(String name, String password);
}
