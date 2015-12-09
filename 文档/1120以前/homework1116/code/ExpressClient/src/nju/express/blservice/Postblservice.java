package nju.express.blservice;

import java.util.Vector;

import nju.express.vo.Post;
import nju.express.vo.User;

public interface Postblservice {
	Vector<Post> getAll();
	Post getById(int id);
	Post add(Post post);
	Post update(Post post);
	boolean delete(int id);
	Vector<User> getCourier();
	Vector<Post> getListByLike(String columnname,Object value);
	Vector<Post> getListBySql(String sql);
	Vector<Post> getListByCondition(String columnname,Object value);
}
