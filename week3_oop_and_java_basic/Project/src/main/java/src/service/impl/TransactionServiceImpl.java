package src.service.impl;

import lombok.Getter;
import lombok.Setter;
import src.constance.Currency;
import src.constance.Status;
import src.constance.TransactionStatus;
import src.input.InputUtils;
import src.model.*;
import src.service.OPTService;
import src.service.TransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class TransactionServiceImpl implements TransactionService {
    private OPTService opt;
    @Getter
    private List<Transaction> transactions;
    private TransactionServiceImpl(OPTService optService,List<Transaction> transactions) {
        opt = optService;
        this.transactions = transactions;
    }
    public static TransactionService getInstance(OPTService optService, List<Transaction> transactions) {
        return new TransactionServiceImpl(optService,transactions);
    }
    @Override
    public void addTransaction(User user,PaymentMethod paymentMethod) {
        if(user.getStatus()== Status.SUSPENDED){
            System.out.println("Tài khoản bị khóa giao dịch!");
            return;
        }
        if(paymentMethod == null){
            System.out.println("Không có phương thức giao dịch");
            return;
        }
        int id = InputUtils.inputInt("Nhập id mã giao dịch: ");
        for(Transaction transaction : transactions){
            if(transaction.getId() == id){
                System.out.println("id đã tồn tại, tạo giao dịch thất bại!");
                return;
            }
        }
        double amount = InputUtils.inputDouble("Nhập số tiền cần thanh toán: ");
        Transaction transaction = new Transaction(id,user,paymentMethod,amount, LocalDateTime.now(), Currency.USD, TransactionStatus.PENDING);
        if(amount >= 5000 && !opt.checkOTP()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }
        if(!paymentMethod.processPaymentMethod(amount)){
            System.out.println("Số dư không đủ hệ thống chia nhỏ thanh toán!");
            amount = paymentMethod.spilitProcess(amount);
            for(PaymentMethod pay : user.getPaymentMethods().values()){
                if(pay.getId() != paymentMethod.getId()){
                    if(!pay.processPaymentMethod(amount)){
                        amount = pay.spilitProcess(amount);
                        System.out.println("Số tiền đã đc chuyển nhỏ thanh toán vào phương thức: "+pay.getId());
                    } else {
                        System.out.println("Số tiền đã được chuyển qua thanh toán ở phương thức có id: "+pay.getId());
                        amount = 0;
                    }
                }
            }
            if(amount>0){
                System.out.println("Hiện tại không thể thanh toán tiếp số dư này nên sẽ tính vào khoản vay: "+amount);
                user.setLoan(user.getLoan()+amount);
            }
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
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
                if(t.getTransactionDateTime().isAfter(LocalDateTime.now().minusDays(7)) && t.getUser().equals(user)){
                    transaction7days.add(t);
                }
            }
            System.out.println("Danh sách giao dịch trong 7 ngày gần nhất:");
            for(Transaction t : transaction7days){
                System.out.println(t);
            }
        }
    }


    @Override
    public void showPaymentMethod(User user) {
         for(PaymentMethod pm : user.getPaymentMethods().values()){
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
            if(t.getTransactionDateTime().isAfter(LocalDateTime.now().minusMonths(1)) && t.getUser().equals(user)){
                System.out.println(t);
            }
        }
    }

    @Override
    public void showSuspendTransaction(User user) {
        System.out.println("Giao dịch nghi gian lận: ");
        for(Transaction t : transactions){
            if(t.getTransactionDateTime().isAfter(LocalDateTime.now().minusDays(1)) && t.getUser().equals(user)){
                System.out.println(t);
            }
        }
    }


}
