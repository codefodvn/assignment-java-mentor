package src.service.impl;

import src.constance.Status;
import src.model.*;
import src.service.UserService;

import java.util.List;
import java.util.Random;

public class UserServiceImpl implements UserService {
    private int attemptLogin = 0;
    List<User> users;

    public UserServiceImpl(List<User> list) {
        users = list;
    }

    @Override
    public User login() {
        String username =null;
        String password = null;
        while(attemptLogin<3){
             username = InputUtils.inputString("Tài khoản: ");
             password = InputUtils.inputString("Mật khẩu: ");
            for (User u : users) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    if(u.getStatus()==Status.BANNED) {
                        System.out.println("Tài khoản bị ban rồi nhé!");
                        return null;
                    }
                    System.out.println("Login thành công");
                    attemptLogin = 0;
                    return u;
                }
            }

            attemptLogin++;
            System.out.printf("Login thất bại, bạn còn %d lần nữa. \n" ,3- attemptLogin);
        }
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(username) ) {
                user = u;
            }
        }
        System.out.println("Tài khoản bị khóa do đăng nhập sai quá 3 lần!");
        attemptLogin=0;
        if(user!=null)user.setStatus(Status.BANNED);
        return null;
    }

    @Override
    public List<Transaction> getTransactions(User user) {
        return List.of();
    }

    @Override
    public boolean checkTransactionInMinute(List<Transaction> transactions) {
        return false;
    }

    @Override
    public void resetPassword(User user) {
        String newPass = InputUtils.inputString("Nhập mật khẩu mới: ");
        user.setPassword(newPass);
    }





}
