package src.service;

import src.model.Transaction;
import src.model.User;

import java.util.List;

public interface UserService {
    User login();
    List<Transaction> getTransactions(User user);
    boolean checkTransactionInMinute(List<Transaction> transactions);
    void resetPassword(User user);
}
