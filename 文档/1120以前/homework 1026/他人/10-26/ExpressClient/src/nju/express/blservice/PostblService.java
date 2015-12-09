package nju.express.blservice;

import javax.sound.midi.Receiver;

import nju.express.vo.Goods;
import nju.express.vo.Post;
import nju.express.vo.Sender;

public interface PostblService {
    void creatPost(Sender sender,Receiver receiver,
    		Goods goods,String type,double packingexpense,String username,String datetime);
    Post checkPost(int id);
}
