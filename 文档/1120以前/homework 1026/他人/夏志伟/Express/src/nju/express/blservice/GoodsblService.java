package nju.express.blservice;

import nju.express.vo.Goods;

public interface GoodsblService {
	void addGoods(Goods goods);

	void deleteGoods(int id);

	void updateGoods(int id, Goods goods);
	
	Goods getGoods(int id);
}
