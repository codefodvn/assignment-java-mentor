package src.service;

import src.model.Transaction;
import src.model.User;

import java.util.List;

public interface UserService {
    void login(String username, String password);
    List<Transaction> getTransactions(User user);
}
