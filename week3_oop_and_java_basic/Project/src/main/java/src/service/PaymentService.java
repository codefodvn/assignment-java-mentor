package src.service;

import src.model.PaymentMethod;
import src.model.User;

public interface PaymentService {
    void addPayment(User user);
    void removePayment(User user);
    PaymentMethod choosePaymentMethod(User user);
}
