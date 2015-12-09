package nju.express.blservice;

import java.util.Vector;

import nju.express.po.Post;
import nju.express.po.User;

public interface Postblservice {
	Vector<Post> getAll();

	Vector<Post> getBySql(String sql, Object[] values);

	Post getById(int id);

	int add(Post post);

	boolean update(Post post);

	boolean update_fk_id(String sql, Object[] obs);

	boolean delete(int id);

	Vector<User> getCourier(int department_id);

	Vector<Post> getListByLike(String columnname, Object value);

	Vector<Post> getListBySql(String sql);

	Vector<Post> getListByCondition(String columnname, Object value);

	Post fk_id(Post post);
}
