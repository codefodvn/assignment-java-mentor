/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import constance.UserStatus;
import model.User;
import service.impl.OTPServiceImpl;
import service.impl.PaymentServiceImpl;
import service.impl.UserServiceImpl;
import view.Input;
import view.Menu;

/**
 *
 * @author macos
 */
public class PaymentApplication{
    private UserServiceImpl usv = new UserServiceImpl();
    
    public void mainMenu(User usr){
        class MainMenu extends Menu{
            public MainMenu(){
                super("Main menu", new String[]{
                    "Payment",
                    "Refund",
                    "Report",
                    "Setting",
                    "Login out"
                });
            }
            
            @Override
            public void execute(int choice){
                switch(choice){
                    case 1:
                        payment(usr);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5: 
                        this.stop();
                        break;
                                
                }
            }            
        }
        MainMenu menu = new MainMenu();
        menu.run();
    }
    public void login(){
        User user = null;
        do{
            boolean valid = false;
            String userName = Input.getAndValidUserName("Enter");
            for(User usr : usv.getAll()){
                if(userName.equals(usr.getUserName())) {
                    user = usr;
                    valid = true;
                    break;
                }
            }
            if(valid) break;
            System.out.println("Wrong Username!");
        }while(true);
        
        if(user.getStatus().getCurrentStatus().equals("Banned")){
            System.out.println("Your account has been banned!");
            return;
        }
        do{
            boolean valid = false;
            String password = Input.getAndValidPassword("Enter");
            for(User usr :usv.getAll()){
                if(password.equals(usr.getPassword())) {
                    valid = true;
                    break;
                }
            }
            if(valid) break;
            System.out.println("Password not match!");
        }while(true);
        mainMenu(user);
    }
    
    public void payment(User usr){
        OTPServiceImpl osv = new OTPServiceImpl();
        PaymentServiceImpl psv = new PaymentServiceImpl();
        class SubMenu extends Menu{
            private double money;
            public SubMenu(){
                super("Choose payment method: ", new String[]{
                    "Bank Transfer "+usr.getBank().toString(),
                    "Credit Card "+usr.getCredit().toString(),
                    "EWallet "+usr.getWallet().toString(),
                    "Exit"
                        
                });
            }
            
            public void getMoney(){
                money = Input.getAndValidNumber("Enter money: ");
                if( money>5000 )
                    if(osv.checkOTP(usr)) return;
            }
            
            @Override
            public void execute(int choice){
                switch(choice){
                    case 1:
                        getMoney();
                        if(!psv.payByBank(usr,money))
                            psv.payByDivide(usr, money);
                        break;
                    case 2:
                        getMoney();
                        if(!psv.payByCard(usr,money))
                            psv.payByDivide(usr, money);
                        
                        break;
                    case 3:
                        getMoney();
                        if(!psv.payByEWallet(usr,money))
                            psv.payByDivide(usr, money);
                        
                        break;
                    case 4:
                        this.stop();
                        break;
                    
                        
                }
            }
            
        }
        SubMenu subMenu = new SubMenu();
        subMenu.run();
    }
    
    
}
