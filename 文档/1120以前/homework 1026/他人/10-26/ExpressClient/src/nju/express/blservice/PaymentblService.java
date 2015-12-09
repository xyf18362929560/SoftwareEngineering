package nju.express.blservice;

import nju.express.vo.Payment;

public interface PaymentblService {
    void creatPayment(String datetime,double amount,String name,String type);
    Payment checkPayment(int id);
    double statistics(String starttime,String endtime);
}
