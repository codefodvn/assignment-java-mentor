package src.handler;

import src.constance.Status;
import src.model.Menu;
import src.model.User;
import src.service.FraudDetectionService;
import src.service.TransactionService;

public class FinanceMenuHandler {
    private final TransactionService transactionService;
    private final FraudDetectionService fraudDetectionService;

    public FinanceMenuHandler(TransactionService transactionService, FraudDetectionService fraudDetectionService) {
        this.transactionService = transactionService;
        this.fraudDetectionService = fraudDetectionService;
    }

    public void displayFinanceMenu(User user) {
        Menu menu = new Menu();
        menu.add("Hiển thị tổng số tiền theo từng phương thức");
        menu.add("Hiển thị số lần thanh toán thành công/thất bại");
        menu.add("Hiển thị giao dịch nghi ngờ gian lận");
        menu.add("Xuất báo cáo theo tháng");
        menu.add("Quay lại menu chính");

        int choice;
        do {
            choice = menu.getUserChoice(5);
            switch (choice) {
                case 1: transactionService.showPaymentMethod(user); break;
                case 2: transactionService.showTransactionSuccessAndFailed(user); break;
                case 3:
                    if (fraudDetectionService.checkUser(user)) {
                        user.setStatus(Status.SUSPENDED);
                        transactionService.showSuspendTransaction(user);
                    } else {
                        System.out.println("Không có giao dịch bất thường.");
                    }
                    break;
                case 4: transactionService.showTransactionMonthly(user); break;
            }
        } while(choice >0 && choice <5);
    }
}