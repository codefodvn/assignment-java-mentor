package src.main;

import src.constance.Status;
import src.model.InputUtils;
import src.model.Menu;
import src.model.PaymentMethod;
import src.model.User;
import src.service.RefundService;
import src.service.TransactionService;
import src.service.impl.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OPTServiceImpl opt = new OPTServiceImpl();
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        TransactionServiceImpl transactionService = new TransactionServiceImpl(opt);
        RefundService refundService = new RefundServiceImpl();
        User u1 = new User(1,"minh1","123","Minh", Status.ACTIVE,new ArrayList<>());
        User u2 = new User(2,"minh2","123","Minh", Status.ACTIVE,new ArrayList<>());
        User u3 = new User(3,"minh3","123","Minh", Status.ACTIVE,new ArrayList<>());
        List<User> users = new ArrayList<User>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        User userlogin = null;
        FraudDetectionServiceImpl fraudDetectionService = new FraudDetectionServiceImpl();
        UserServiceImpl userService = new UserServiceImpl(users);
        Menu menu = new Menu();
        menu.add("Đăng nhập");
        menu.add("Thoát");
        int choice;
        do {
            System.out.println("");
            System.out.println("Hệ thống vip pro có 1 0 2");

            choice = menu.getUserChoice(2);
            switch(choice){
                case 1:userlogin=userService.login();
                    if(userlogin!=null){
                        Menu menu1 = new Menu();
                        menu1.add("Thực hiện thanh toán");
                        menu1.add("Yêu cầu hoàn tiền");
                        menu1.add("Báo cáo tài chính");
                        menu1.add("Cài đặt tài khoản");
                        menu1.add("Đăng xuất");
                        int choice2;
                        do {
                             choice2 = menu1.getUserChoice(5);
                             switch(choice2){
                                 case 1:
                                     PaymentMethod paymentMethod = paymentService.choosePaymentMethod(userlogin);
                                     transactionService.addTransaction(userlogin,paymentMethod);
                                 break;
                                 case 2:
                                     transactionService.showTransactionIn7Days(userlogin);
                                     refundService.refund();
                                 break;
                                 case 3:
                                     Menu menu3 = new Menu();
                                     menu3.add("Hiển thị tổng số tiền đã thanh toán theo từng phương thức");
                                     menu3.add("Hiển thị số lần thanh toán thành công/thất bại");
                                     menu3.add("Hiển thị danh sách giao dịch nghi ngờ gian lận");
                                     menu3.add("Nếu là tài khoản doanh nghiệp → Xuất báo cáo theo tháng");
                                     menu3.add("Quay lại menu chính");
                                     int choice3;
                                     do {
                                         choice3 = menu3.getUserChoice(5);
                                         switch(choice3){
                                             case 1:
                                                 transactionService.showPaymentMethod(userlogin);
                                                 break;
                                             case 2:
                                                 transactionService.showTransactionSuccessAndFailed(userlogin);
                                                 break;
                                             case 3:
                                                 if(fraudDetectionService.checkUser(userlogin)){
                                                     System.out.println("Tài khoản đang bị nghi ngờ!");
                                                     transactionService.showSuspendTransaction(userlogin);
                                                 }
                                                 else{
                                                     System.out.println("Hôm nay không thấy giao dịch bất thường");
                                                 }

                                                 break;
                                             case 4:
                                                 transactionService.showTransactionMonthly(userlogin);

                                                 break;
                                             default:
                                                 break;
                                         }}
                                     while(choice3 >0 && choice3 <5);
                                 break;
                                 case 4:
                                    Menu menu4 = new Menu();
                                    menu4.add("Thay đổi mật khẩu");
                                    menu4.add("Thêm phương thức thanh toán mới");
                                    menu4.add("Xóa phương thức thanh toán cũ");
                                    menu4.add("Xem thông tin bảo mật tài khoản");
                                    menu4.add("Quay lại menu chính");
                                    int choice4;
                                    do {
                                        choice4 = menu4.getUserChoice(5);
                                        switch(choice4){
                                            case 1:
                                                userService.resetPassword(userlogin);
                                                break;
                                                case 2:
                                                    paymentService.addPayment(userlogin);
                                                    break;
                                                    case 3:
                                                        paymentService.removePayment(userlogin);
                                                        break;
                                                        case 4:
                                                            System.out.println(userlogin);
                                                            break;
                                            default:
                                                                break;
                                        }}
                                        while(choice4 >0 && choice4 <5);
                                 break;
                                 case 5:
                                 break;
                                 default:
                                     break;
                             }
                        } while(choice2 > 0 && choice2 <5);


                    } else System.out.println("Bye bye");
                    break;
                default: System.out.println("Bye bye");
                    userlogin=null;
                    break;

            }
        } while (choice>0 && choice <2);
    }
}