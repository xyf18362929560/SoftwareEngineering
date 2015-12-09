package nju.express.blservice;

import nju.express.vo.Sender;

public interface SenderblService {
    void createSender(String name,String address,String phone);
    Sender checkSender(int id);
}
