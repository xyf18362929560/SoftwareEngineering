package nju.express.blservice;

import nju.express.vo.Recipient;

public interface RecipientblService {
    void createRecipient(String name,String datetime);
    Recipient checkRecipient(int id); 
}
