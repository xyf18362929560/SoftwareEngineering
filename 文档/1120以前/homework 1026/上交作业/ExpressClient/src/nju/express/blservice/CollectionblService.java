package nju.express.blservice;

import nju.express.vo.Collection;

public interface CollectionblService {
	void addCollection(Collection collection);
	
	void deleteCollection(int id);
	
	void updateCollection(int id, Collection collection);
	
	Collection getCollection(int id);
}
