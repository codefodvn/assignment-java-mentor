package src.service;

import src.model.Transaction;

import java.util.List;
import java.util.PriorityQueue;

public interface RefundService {
    public void refund();
    public PriorityQueue<Transaction> getTransactionRefund();
}
