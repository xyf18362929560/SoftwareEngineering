package nju.express.blservice;

import javax.sound.midi.Receiver;

public interface ReceiverblService {
	void createReceiver(String name,String address,String phone);
	Receiver checkReceiver();
}
