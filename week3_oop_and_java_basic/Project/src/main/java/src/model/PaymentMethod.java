package src.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class PaymentMethod {
    int id;
    double balance;
    public abstract boolean processPaymentMethod(double amount);

    public abstract double spilitProcess(double amount);
    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
