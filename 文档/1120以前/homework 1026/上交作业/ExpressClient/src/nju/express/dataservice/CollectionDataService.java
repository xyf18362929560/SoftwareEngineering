package nju.express.dataservice;

import nju.express.vo.Collection;

public interface CollectionDataService {
	void addCollection(Collection collection);

	void deleteCollection(int id);

	void updateCollection(int id, Collection collection);

	Collection getCollection(int id);
}
