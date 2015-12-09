package nju.express.dataservice;

import nju.express.vo.Goods;

public interface GoodsDataService {
	void addGoods(Goods goods);

	void deleteGoods(int id);

	void updateGoods(int id, Goods goods);

	Goods getGoods(int id);
}
