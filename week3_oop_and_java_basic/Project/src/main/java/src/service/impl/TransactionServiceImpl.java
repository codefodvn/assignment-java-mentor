package src.service.impl;

import src.constance.Currency;
import src.constance.TransactionStatus;
import src.model.InputUtils;
import src.model.PaymentMethod;
import src.model.Transaction;
import src.model.User;
import src.service.TransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private OPTServiceImpl opt;
    public static List<Transaction> transactions = new ArrayList<>();
    public TransactionServiceImpl(OPTServiceImpl optService){
        opt = optService;
    }
    @Override
    public void addTransaction(User user,PaymentMethod paymentMethod) {
        if(paymentMethod == null){
            System.out.println("Không có phương thức giao dịch");
            return;
        }
        int id = InputUtils.inputInt("Nhập id mã giao dịch: ");
        double amount = InputUtils.inputDouble("Nhập số tiền cần thanh toán: ");
        Transaction transaction = new Transaction(id,user,paymentMethod,amount, LocalDateTime.now(), Currency.USD, TransactionStatus.PENDING);
        if(amount > 5000 && !opt.checkOTP()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }
        if(amount > paymentMethod.getBalance()){
            System.out.println("Số dư không đủ bạn hãy chuyển sang phương thức khác hoặc chia nhỏ thanh toán!");
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        } else{
            System.out.println("Thanh toán thành công!");
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }
        transactions.add(transaction);
    }

    @Override
    public void showTransactionIn7Days(User user) {
        if (transactions.isEmpty()) {
            System.out.println("Không có giao dịch nào!");
        } else {
            List<Transaction> transaction7days = new ArrayList<>();
            for (Transaction t : transactions) {
                if(t.getTransactionDateTime().isAfter(LocalDateTime.now().minusDays(7))){
                    transaction7days.add(t);
                }
            }
            System.out.println("Danh sách giao dịch:");
            for(Transaction t : transaction7days){
                System.out.println(t);
            }
        }
    }

    @Override
    public void showPaymentMethod(User user) {
         for(PaymentMethod pm : user.getPaymentMethods()){
             System.out.println(pm);
             int money = 0;
             for(Transaction t : transactions){
                 if(t.getPaymentMethod().equals(pm) && t.getTransactionStatus().equals(TransactionStatus.SUCCESS)){
                     money += t.getAmount();
                 }
             }
             System.out.println("Tổng số tiền đã giao dịch: "+money);
         }
    }

    @Override
    public void showTransactionSuccessAndFailed(User user) {
        int success = 0;
        int failed = 0;
        for(Transaction t : transactions){
            if(t.getTransactionStatus().equals(TransactionStatus.SUCCESS) && t.getUser().equals(user)){
                success ++;
            } else if(t.getTransactionStatus().equals(TransactionStatus.FAILED) && t.getUser().equals(user)){
                failed++;
            }
        }
        System.out.println("Tổng số lần thanh toán thành công: "+success);
        System.out.println("Tổng số lần thanh toán thất bại: "+failed);
    }

    @Override
    public void showTransactionMonthly(User user) {
        System.out.println("Giao dịch trong tháng qua: ");
        for(Transaction t : transactions){
            if(t.getTransactionDateTime().isAfter(LocalDateTime.now().minusMonths(1))){
                System.out.println(t);
            }
        }
    }
}
