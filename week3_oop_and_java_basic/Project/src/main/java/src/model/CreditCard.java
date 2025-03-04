package src.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditCard extends PaymentMethod{
    private double creditLimit;

    public CreditCard(int id, double balance) {
        super(id, balance);
    }

    @Override
    public boolean processPaymentMethod(double amount) {
        if(getBalance()+creditLimit<=amount){
            return false;
        };
        setBalance(getBalance()-amount);
        return true;
    }
}
