package src.service.impl;

import lombok.Getter;
import lombok.Setter;
import src.model.InputUtils;
import src.service.OPTService;

import java.util.Random;
@Getter
@Setter
public class OPTServiceImpl implements OPTService {
    private int wrongAttempt=0;
    @Override
    public boolean checkOTP() {
        int otp = new Random().nextInt(10000);
        System.out.println("Mã otp của bạn là : "+otp);

        while(wrongAttempt<3){
        int userInput = InputUtils.inputInt("Nhập mã OTP: ");
        if (otp == userInput) {
            System.out.println("OTP chính xác!");
            wrongAttempt = 0;
            return true;
        } else {
            wrongAttempt++;
            System.out.printf("OTP sai, bạn còn %d lần nữa. \n" ,3- wrongAttempt);
        }
        }
        System.out.println("Nhập mã OTP thất bại, giao dịch thất bại!");
        wrongAttempt = 0;
        return false;

    }
}
