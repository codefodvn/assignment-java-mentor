package src.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EWallet extends PaymentMethod{
    final double amountMaxPerDay = 5000;

    public EWallet(String id, double balance, User user) {
        super(id, balance, user);
    }

    @Override
    public boolean processPaymentMethod(double amount) {
        if(amount>amountMaxPerDay) return false;
        return true;
    }
}
