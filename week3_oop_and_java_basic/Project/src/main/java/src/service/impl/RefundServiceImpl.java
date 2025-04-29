package src.service.impl;

import lombok.Getter;
import lombok.Setter;
import src.constance.Currency;
import src.constance.TransactionStatus;
import src.input.InputUtils;
import src.model.Transaction;
import src.service.RefundService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@Setter
public class RefundServiceImpl implements RefundService {
    private List<Transaction> transactions;
    private RefundServiceImpl(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public static RefundService getInstance(List<Transaction> transactions) {
        return  new RefundServiceImpl(transactions);
    }
    @Override
    public void refund() {
        int id = InputUtils.inputInt("Nhập id của giao dịch cần hoàn tiền: ");
        for (Transaction trans: transactions){
            if(trans.getId() == id){
                if(trans.getTransactionDateTime().isAfter(LocalDateTime.now().minusDays(7)) && trans.getTransactionStatus()==TransactionStatus.SUCCESS){
                    Transaction transaction = new Transaction(trans.getId()+999,trans.getUser(),trans.getPaymentMethod(),trans.getAmount(),
                            LocalDateTime.now(), Currency.USD,TransactionStatus.REFUNDED);
                    trans.getPaymentMethod().setBalance(trans.getPaymentMethod().getBalance()+trans.getAmount());
                    transactions.add(transaction);
                    System.out.println("Hoàn tiền thành công");
                    return;
                }
            }
        }
        System.out.println("id giao dịch không hợp lệ, hoàn tiền thất bại");
    }

    @Override
    public PriorityQueue<Transaction> getTransactionRefund() {
        PriorityQueue<Transaction> queue = new PriorityQueue<>(
                Comparator.comparing((Transaction t) -> t.getAmount()>10000)
                        //.thenComparingInt() giao dịch nội địa chưa xử lí ihihi :)

        );

        for (Transaction trans: transactions){
            if(trans.getTransactionStatus()==TransactionStatus.REFUNDED){
                queue.add(trans);
            }
        }

        return queue;
    }

}
