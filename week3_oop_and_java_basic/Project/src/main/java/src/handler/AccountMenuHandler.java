package src.handler;

import src.model.Menu;
import src.model.User;
import src.service.PaymentService;
import src.service.UserService;

public class AccountMenuHandler {
    private final UserService userService;
    private final PaymentService paymentService;

    public AccountMenuHandler(UserService userService, PaymentService paymentService) {
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public void displayAccountMenu(User user) {
        Menu menu = new Menu();
        menu.add("Thay đổi mật khẩu");
        menu.add("Thêm phương thức thanh toán");
        menu.add("Xóa phương thức thanh toán");
        menu.add("Xem thông tin tài khoản");
        menu.add("Quay lại");

        int choice;
        do {
            choice = menu.getUserChoice(5);
            switch (choice) {
                case 1: userService.resetPassword(user); break;
                case 2: paymentService.addPayment(user); break;
                case 3: paymentService.removePayment(user); break;
                case 4: System.out.println(user); break;
            }
        } while (choice > 0 && choice < 5);
    }
}