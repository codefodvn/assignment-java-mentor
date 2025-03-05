package src.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import src.constance.Status;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User {
    int id;
    String username;
    String password;
    String name;
    Status status;
    List<PaymentMethod> paymentMethods;

    @Override
    public String toString() {
        StringBuilder paymentStr = new StringBuilder();
        if (paymentMethods != null && !paymentMethods.isEmpty()) {
            paymentStr.append("[");
            for (PaymentMethod pm : paymentMethods) {
                paymentStr.append(pm.toString()).append(", ");
            }
            paymentStr.delete(paymentStr.length() - 2, paymentStr.length());
            paymentStr.append("]");
        } else {
            paymentStr.append("Không có phương thức thanh toán");
        }


        return
                "id: " + id +
                ", username: '" + username + '\'' +
                ", password: '" + password + '\'' +
                ", tên:'" + name + '\'' +
                ", paymentMethods=" + paymentStr +
                '}';
    }
}
