package src.handler;

import src.model.Menu;
import src.model.PaymentMethod;
import src.model.User;
import src.service.*;

public class UserMenuHandler {
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final RefundService refundService;
    private final FinanceMenuHandler financeMenuHandler;
    private final AccountMenuHandler accountMenuHandler;

    public UserMenuHandler(PaymentService paymentService, TransactionService transactionService,
                           RefundService refundService, FinanceMenuHandler financeMenuHandler,
                           AccountMenuHandler accountMenuHandler) {
        this.paymentService = paymentService;
        this.transactionService = transactionService;
        this.refundService = refundService;
        this.financeMenuHandler = financeMenuHandler;
        this.accountMenuHandler = accountMenuHandler;
    }

    public void displayUserMenu(User user) {
        Menu menu = new Menu();
        menu.add("Thực hiện thanh toán");
        menu.add("Yêu cầu hoàn tiền");
        menu.add("Báo cáo tài chính");
        menu.add("Cài đặt tài khoản");
        menu.add("Đăng xuất");

        int choice;
        do {
            choice = menu.getUserChoice(5);
            switch (choice) {
                case 1:
                    PaymentMethod paymentMethod = paymentService.choosePaymentMethod(user);
                    transactionService.addTransaction(user, paymentMethod);
                    break;
                case 2:
                    transactionService.showTransactionIn7Days(user);
                    refundService.refund();
                    break;
                case 3:
                    financeMenuHandler.displayFinanceMenu(user);
                    break;
                case 4:
                    accountMenuHandler.displayAccountMenu(user);
                    break;
                case 5:
                    System.out.println("Đăng xuất thành công.");
                    break;
            }
        } while (choice >0 && choice <5);
    }
}