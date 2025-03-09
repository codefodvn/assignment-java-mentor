/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import constance.UserStatus;
import java.util.Random;
import model.User;
import service.OTPService;
import view.Input;

/**
 *
 * @author macos
 */
public class OTPServiceImpl implements OTPService {
    private UserServiceImpl usv = new UserServiceImpl();
    
    private int otp;
    @Override
    public int getOTP(){
        Random random = new Random();
        otp = 100000 + random.nextInt(900000);
        return otp;
    }
    public boolean checkOTP(User usr){
        User user = usr;
        for(int i =2;i>-1;i--){
            System.out.println("Your OTP is: "+getOTP());
            int input = (int)Input.getAndValidNumber("Please enter OTP: ");
            if(input == otp) return true;
            else {
                System.out.println("Your chances left: "+i);
            }
        } 

        user.setStatus(new UserStatus("Banned"));
        usv.update(user);
        System.out.println("Your account has been locked!");
        return false;
    }
}
