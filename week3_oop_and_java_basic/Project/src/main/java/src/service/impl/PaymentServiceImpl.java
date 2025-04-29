package src.service.impl;

import lombok.Getter;
import lombok.Setter;
import src.input.InputUtils;
import src.model.*;
import src.service.PaymentService;
@Getter
@Setter
public class PaymentServiceImpl implements PaymentService {
    private PaymentServiceImpl(){

    }
    public static PaymentService getInstance(){
        return new PaymentServiceImpl();
    }
    @Override
    public void addPayment(User user) {
        System.out.println("=== Chọn phương thức thanh toán ===");
        System.out.println("1. Ví điện tử");
        System.out.println("2. Ngân hàng");
        System.out.println("3. Thẻ tín dụng");
        double balance = 50000;
        int choice;
        while (true) {

            choice = InputUtils.inputInt("Nhập lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    int stk =  InputUtils.inputId("Nhập id phương thức thanh toán: ");
                    for(PaymentMethod pay : user.getPaymentMethods().values()){
                        if(pay.getId()==stk) {
                            System.out.println("Id đã tồn tại, tạo mới thất bại!");
                            return;
                        }
                    }


                    EWallet eWallet = new EWallet(stk,balance);
                    user.getPaymentMethods().put(stk,eWallet);
                    System.out.println("Thêm phương thức thành công");
                    break;
                case 2:
                    int stkBank =  InputUtils.inputId("Nhập id phương thức thanh toán: ");
                    for(PaymentMethod pay : user.getPaymentMethods().values()){
                        if(pay.getId()==stkBank) {
                            System.out.println("Id đã tồn tại, tạo mới thất bại!");
                            return;
                        }
                    }
                    BankTransfer bank = new BankTransfer(stkBank,balance);
                    user.getPaymentMethods().put(stkBank,bank);
                    System.out.println("Thêm phương thức thành công");
                    break;

                case 3:
                    int stkCard =  InputUtils.inputId("Nhập id phương thức thanh toán: ");
                    for(PaymentMethod pay : user.getPaymentMethods().values()){
                        if(pay.getId()==stkCard) {
                            System.out.println("Id đã tồn tại, tạo mới thất bại!");
                            return;
                        }
                    }
                    CreditCard card = new CreditCard(stkCard,balance);
                    user.getPaymentMethods().put(stkCard,card);
                    System.out.println("Thêm phương thức thành công");
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
        for(PaymentMethod pay : user.getPaymentMethods().values()) {
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
        for(PaymentMethod pay : user.getPaymentMethods().values()) {
            System.out.println(pay);
        }
        int k = InputUtils.inputId("Điền id tài khoản bạn muốn thanh toán: ");
        return  user.getPaymentMethods().get(k);
    }

}
