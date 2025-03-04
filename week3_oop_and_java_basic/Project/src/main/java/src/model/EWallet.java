package src.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EWallet extends PaymentMethod{
    final double amountMaxPerDay = 5000;

    public EWallet(int id, double balance) {
        super(id, balance);
    }

    @Override
    public boolean processPaymentMethod(double amount) {
        if(amount>amountMaxPerDay) return false;
        return true;
    }
}
