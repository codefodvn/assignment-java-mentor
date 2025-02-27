/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import constance.UserStatus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.*;
import service.UserService;
import view.Input;

/**
 *
 * @author macos
 */
public class UserServiceImpl implements UserService<User>{
    private static final String FILE_PATH = "user.txt";
    private  ArrayList<User> users = new ArrayList<>();

    public UserServiceImpl() {
        users = getAll();
    }
    
    
    public  ArrayList<User> getAll(){
        ArrayList<User> result = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                User user = new User(data[0],data[1],data[2],new UserStatus(data[3]),new BankTransfer(Double.parseDouble(data[4])),new CreditCard(Double.parseDouble(data[5]),Double.parseDouble(data[6]),Double.parseDouble(data[7])),new EWallet(Double.parseDouble(data[8]),LocalDate.parse(data[9]),Double.parseDouble(data[10])));
                result.add(user);
            }
            br.close();
        } catch (IOException e) {
        }
        return result;
    }
    
    @Override
     public void update(User cus) {
        List<User> customers= users;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User c : customers) {
                if (c.getUserID().equals(cus.getUserID())) {
                    bw.write(cus.toCSV());
                    } else {
                    bw.write(c.toCSV());
                }
            bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        users = getAll();
    }


    @Override
    public void changePassword(User user) {
        User temp = user;
        String oldPassword = Input.getAndValidPassword("Enter your old");
        if(!oldPassword.equals(user.getPassword())){
            System.out.println("Password not matched!");
            return;
        }
        String newPassword = Input.getAndValidPassword("Enter your new");
        temp.setPassword(newPassword);
        update(temp);
        do{
            String reNewPassword = Input.getAndValidPassword("Confirm your new");
            if(newPassword.equals(reNewPassword)){
                System.out.println("Password change successfully!");
                return;
            }else{
                System.out.println("Password not matched!");
            }
        }while(true);
        
    }   

    @Override
    public void addNewPayment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getUserStatus(User user) {
       System.out.println("User ID: "+user.getUserID());
       System.out.println("Username: " +user.getUserName());
       System.out.println("User password: "+user.getPassword());
       System.out.println("Status: "+user.getStatus());
    }

    public  ArrayList<User> getUsers() {
        return users;
    }

    
}
