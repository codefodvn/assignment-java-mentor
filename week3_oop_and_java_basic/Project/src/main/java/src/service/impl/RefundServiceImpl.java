package src.service.impl;

import src.constance.Currency;
import src.constance.TransactionStatus;
import src.model.InputUtils;
import src.model.Transaction;
import src.service.RefundService;

import java.time.LocalDateTime;

public class RefundServiceImpl implements RefundService {

    @Override
    public void refund() {
        int id = InputUtils.inputInt("Nhập id của giao dịch cần hoàn tiền: ");
        for (Transaction trans: TransactionServiceImpl.transactions){
            if(trans.getId() == id){
                if(trans.getTransactionDateTime().isAfter(LocalDateTime.now().minusDays(7))){
                    Transaction transaction = new Transaction(trans.getId()+999,trans.getUser(),trans.getPaymentMethod(),trans.getAmount(),
                            LocalDateTime.now(), Currency.USD,TransactionStatus.REFUNDED);
                    trans.getPaymentMethod().setBalance(trans.getPaymentMethod().getBalance()+trans.getAmount());
                    TransactionServiceImpl.transactions.add(transaction);
                    System.out.println("Hoàn tiền thành công");
                    return;
                }
            }
        }
        System.out.println("id giao dịch không hợp lệ, hoàn tiền thất bại");
    }
}
