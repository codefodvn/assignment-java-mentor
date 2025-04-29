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
public class BankTransfer extends PaymentMethod{
    private static final double FEE_THRESHOLD = 2000;
    private static final double FEE_PERCENTAGE = 0.01;

    public BankTransfer(int id, double balance ) {
        super(id, balance);
    }

    @Override
    public boolean processPaymentMethod(double amount) {
        double finalAmount = (amount > FEE_THRESHOLD) ? amount * (1 + FEE_PERCENTAGE) : amount;
        if(finalAmount > getBalance()) return false;
        setBalance(getBalance() - finalAmount);
        return true;
    }

    @Override
    public double spilitProcess(double amount) {
        double finalAmount ;
        if (getBalance()>FEE_THRESHOLD){
            finalAmount =(amount > FEE_THRESHOLD) ? amount * (1 + FEE_PERCENTAGE) : amount;
        } else finalAmount = amount;
        double minusAmount = getBalance();
        setBalance(0);
        amount= finalAmount - minusAmount;
            return amount;
    }


    @Override
    public String toString() {
        return "BankTransfer{"+
                "id=" + super.getId() +
                ", balance=" + super.getBalance() +
                '}'
                ;
    }
}
