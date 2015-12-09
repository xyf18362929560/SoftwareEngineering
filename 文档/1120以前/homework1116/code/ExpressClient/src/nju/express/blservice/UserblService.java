package nju.express.blservice;

import nju.express.vo.User;

public interface UserblService {
	User login(String name, String password);
}
