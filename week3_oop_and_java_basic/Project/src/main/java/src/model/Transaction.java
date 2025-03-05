package src.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import src.constance.Currency;
import src.constance.TransactionStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    int id;
    User user;
    PaymentMethod paymentMethod;
    double amount;
    LocalDateTime transactionDateTime;
    Currency currency;
    TransactionStatus transactionStatus;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod.toString() +
                ", amount=" + amount +
                ", transactionDateTime=" + transactionDateTime +
                ", currency=" + currency +
                ", transactionStatus=" + transactionStatus +
                '}';
    }
}
