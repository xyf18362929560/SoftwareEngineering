package nju.express.blservice;

import java.util.Vector;

import nju.express.vo.Collection;

public interface Collectionblservice {
	Vector<Collection> getAll();

	Collection getByid(int id);

	Collection add(Collection collection);

	Collection update(Collection collection);

}
