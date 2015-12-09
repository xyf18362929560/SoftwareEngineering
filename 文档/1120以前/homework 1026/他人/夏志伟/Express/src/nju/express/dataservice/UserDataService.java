package nju.express.dataservice;

import nju.express.vo.User;

/**
 * User���ݷ���ӿ�
 */
public interface UserDataService {
	void addUser(User user);

	void deleteUser(int id);

	void updateUser(int id, User user);

	User getUser(String name, String password);

}
