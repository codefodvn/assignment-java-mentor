package src.service;

import src.model.PaymentMethod;
import src.model.Transaction;
import src.model.User;

public interface TransactionService {
    void addTransaction(User user, PaymentMethod paymentMethod);
    void showTransactionIn7Days(User user);
    void showPaymentMethod(User user);
    void showTransactionSuccessAndFailed(User user);
    void showTransactionMonthly(User user);
    void showSuspendTransaction(User user);
}
