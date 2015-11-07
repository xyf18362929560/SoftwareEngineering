package nju.express.blservice;

import nju.express.po.User;

public interface UserblService {
	User login(String name, String password);
}
