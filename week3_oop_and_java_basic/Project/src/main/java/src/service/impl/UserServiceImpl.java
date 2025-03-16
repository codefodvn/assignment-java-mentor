package src.service.impl;

import src.constance.Status;
import src.input.InputUtils;
import src.model.*;
import src.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    Map<String,Integer> login = new HashMap<String,Integer>();
    List<User> users;

    private UserServiceImpl() {
        DataInitializer();
    }
    public static UserService getInstance() {
        return new UserServiceImpl();
    }
    @Override
    public User login() {
        String username =null;
        String password = null;
        boolean check=false;
             username = InputUtils.inputString("Tài khoản: ");
             password = InputUtils.inputString("Mật khẩu: ");
            for (User u : users) {
                if (u.getUsername().equals(username) ) {
                    check=true;
                    if(u.getStatus()==Status.BANNED) {
                        System.out.println("Tài khoản bị ban rồi nhé!");
                        return null;
                    }
                    else if(u.getPassword().equals(password)) {
                        System.out.println("Login thành công");
                        login.replace(u.getUsername(),0);
                        return u;
                    }
                    else {
                        login.replace(u.getUsername(),login.get(u.getUsername())+1);
                        System.out.println("Login không thành công!");
                        System.out.println("Bạn còn "+(3-login.get(username))+" lần thử lại!" );
                        if(login.get(username)==3) {
                            u.setStatus(Status.BANNED);
                            System.out.println("Tài khoản bạn đã nhập quá số lần quy định, bị ban rồi nhé m!");
                        }
                    }
                }

            }
            if(!check) {
                System.out.println("Không tìm thấy username trong hệ thống!");
            }

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

    @Override
    public void ListLockUser() {
        System.out.println("Danh sách tài khoản bị khóa: ");
        for (User u : users) {
            if(u.getStatus()==Status.SUSPENDED) {
                System.out.println(u);
            }
        }
    }

    private void DataInitializer(){
        User u1 = new User(1,"minh1","123","Minh", Status.ACTIVE,new HashMap<>());
        User u2 = new User(2,"minh2","123","Minh", Status.ACTIVE,new HashMap<>());
        User u3 = new User(3,"minh3","123","Minh", Status.ACTIVE,new HashMap<>());
        users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        login.put(u1.getUsername(),0);
        login.put(u2.getUsername(),0);
        login.put(u3.getUsername(),0);
    }




}
