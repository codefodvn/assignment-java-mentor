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
}
