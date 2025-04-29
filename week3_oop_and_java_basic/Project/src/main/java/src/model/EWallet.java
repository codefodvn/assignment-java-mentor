package src.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EWallet extends PaymentMethod{
    final double amountMaxPerDay = 5000;
    double amountOfday = 0;

    public EWallet(int id, double balance) {
        super(id, balance);
    }

    @Override
    public boolean processPaymentMethod(double amount) {
        if(amount>amountMaxPerDay) {
            System.out.println("Số tiền giao dịch vượt quá tối đa trong ngày");
            return false;
        }
        amountOfday += amount;
        if(amountOfday>amountMaxPerDay) {
            amountOfday -= amount;
            return false;
        }
        else super.setBalance(getBalance()-amountOfday);
        return true;
    }

    @Override
    public double spilitProcess(double amount) {
         double minusAmount ;
         if(getBalance()>(amountMaxPerDay-amountOfday)){
             minusAmount = amountMaxPerDay-amountOfday;
         }
         else{
             minusAmount = getBalance();
         }
         setBalance(getBalance()-minusAmount);
          amount-=minusAmount;
         return amount;
    }

    @Override
    public String toString() {
        return "EWallet{" +
                "amountMaxPerDay=" + amountMaxPerDay +
                "id=" + super.getId() +
                ", balance=" + super.getBalance() +
                '}';
    }
}
