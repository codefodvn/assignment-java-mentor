package src.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.PackagePrivate;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    String username;
    String password;
    String name;
    String email;
    int age;
    Status status;
    Set<EWallet> eWallets;
    Set<BankTransfer> bankTransfers;
    Set<CreditCard> creditCards;
}
