package src.service.impl;

import lombok.Getter;
import lombok.Setter;
import src.constance.Status;
import src.model.PaymentMethod;
import src.model.Transaction;
import src.model.User;
import src.service.FraudDetectionService;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class FraudDetectionServiceImpl implements FraudDetectionService {
    List<Transaction> transactions;
    private FraudDetectionServiceImpl(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public static FraudDetectionService createInstance(List<Transaction> transactions){
        return new FraudDetectionServiceImpl(transactions);
    }
    @Override
    public boolean checkUser(User user) {
        double count1 = 0;
        for(Transaction t : transactions){
            if(t.getTransactionDateTime().isAfter(LocalDateTime.now().minusHours(24)) && t.getUser().equals(user)){
                if(t.getAmount()>10000) {
                    count1++;
                }
            }
        }
        if(count1 >=5){
            return true;
        }
        int count = 0;
        for(PaymentMethod pm : user.getPaymentMethods().values()){
            for(Transaction t : transactions){
                if(t.getPaymentMethod().equals(pm) && t.getTransactionDateTime().isAfter(LocalDateTime.now().minusDays(1))){
                    count++;
                    break;
                }
            }

        }
        System.out.println("Tổng tài khoản giao dịch trong ngày: "+count);
        if(count>=3) {
            return true;
        }

        return false;
    }


}
