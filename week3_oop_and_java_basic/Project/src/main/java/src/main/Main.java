    package src.main;

    import src.constance.Status;
    import src.factory.ServiceManager;
    import src.handler.AccountMenuHandler;
    import src.handler.FinanceMenuHandler;
    import src.handler.UserMenuHandler;
    import src.model.Menu;
    import src.model.PaymentMethod;
    import src.model.User;
    import src.service.*;
    import src.service.impl.*;

    import java.util.ArrayList;
    import java.util.List;


    public class Main {
        public static void main(String[] args) {
            OPTService opt = ServiceManager.getOptService();
            PaymentService paymentService = ServiceManager.getPaymentService();
            TransactionService transactionService = ServiceManager.getTransactionService();
            RefundService refundService = ServiceManager.getRefundService();
            FraudDetectionService fraudDetectionService = ServiceManager.getFraudDetectionService();
            UserService userService = ServiceManager.getUserService();
            AccountMenuHandler accountMenuHandler = new AccountMenuHandler(userService,paymentService);
            FinanceMenuHandler financeMenuHandler = new FinanceMenuHandler(transactionService,fraudDetectionService);
            User userlogin = null;
            Menu menu = new Menu();
            menu.add("Đăng nhập");
            menu.add("Thoát");
            int choice;
            do {
                System.out.println("");
                System.out.println("Hệ thống vip pro có 1 0 2");

                choice = menu.getUserChoice(2);
                switch(choice){
                    case 1:
                        userlogin = userService.login();
                        if(userlogin != null){
                            UserMenuHandler userMenuHandler = new UserMenuHandler(paymentService,transactionService,refundService,financeMenuHandler,accountMenuHandler);
                            userMenuHandler.displayUserMenu(userlogin);
                        }

                    default: System.out.println("Bye bye");
                        break;

                }
            } while (choice == 1);
        }
    }