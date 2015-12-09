package nju.express.blservice.impl;

import javax.sound.midi.Receiver;

import nju.express.blservice.PostblService;
import nju.express.vo.Goods;
import nju.express.vo.Post;
import nju.express.vo.Sender;

public class PostblServiceImpl implements PostblService{

	@Override
	public void creatPost(Sender sender, Receiver receiver, Goods goods,
			String type, double packingexpense, String username, String datetime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post checkPost(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
