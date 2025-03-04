package src.service.impl;

import src.model.*;
import src.service.PaymentService;

import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public void addPayment(User user) {
        System.out.println("=== Chọn phương thức thanh toán ===");
        System.out.println("1. Ví điện tử");
        System.out.println("2. Ngân hàng");
        System.out.println("3. Thẻ tín dụng");
        double balance = 5000;
        int choice;
        while (true) {

            choice = InputUtils.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    int stk =  InputUtils.inputId("Nhập id phương thức thanh toán: ");
                    for(PaymentMethod pay : user.getPaymentMethods()){
                        if(pay.getId()==stk) {
                            System.out.println("Id đã tồn tại, tạo mới thất bại!");
                            return;
                        }
                    }


                    EWallet eWallet = new EWallet(stk,balance);
                    user.getPaymentMethods().add(eWallet);
                    break;
                case 2:
                    int stkBank =  InputUtils.inputId("Nhập id phương thức thanh toán: ");
                    for(PaymentMethod pay : user.getPaymentMethods()){
                        if(pay.getId()==stkBank) {
                            System.out.println("Id đã tồn tại, tạo mới thất bại!");
                            return;
                        }
                    }
                    BankTransfer bank = new BankTransfer(stkBank,balance);
                    user.getPaymentMethods().add(bank);
                    break;

                case 3:
                    int stkCard =  InputUtils.inputId("Nhập id phương thức thanh toán: ");
                    for(PaymentMethod pay : user.getPaymentMethods()){
                        if(pay.getId()==stkCard) {
                            System.out.println("Id đã tồn tại, tạo mới thất bại!");
                            return;
                        }
                    }
                    CreditCard card = new CreditCard(stkCard,balance);
                    user.getPaymentMethods().add(card);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    continue;
            }
            break;
        }
    }

    @Override
    public void removePayment(User user) {
        int stk =  InputUtils.inputId("Nhập id phương thức muốn xóa: ");
        for(PaymentMethod pay : user.getPaymentMethods()) {
            if(pay.getId()==stk) {
                user.getPaymentMethods().remove(pay);
                System.out.println("Xóa phương thức thành công");
                return;
            }
        }
        System.out.println("Không tìm thấy phương thức cần xóa");
        return;
    }

    @Override
    public PaymentMethod choosePaymentMethod(User user) {
        Menu menu = new Menu();
        if(user.getPaymentMethods().isEmpty()) {
            System.out.println("Trống trơn");
            return null;
        }
        for(PaymentMethod pay : user.getPaymentMethods()) {
            menu.add(pay.toString());
        }
        int k = menu.getUserChoice(user.getPaymentMethods().size());
        return  user.getPaymentMethods().get(k);
    }

}
