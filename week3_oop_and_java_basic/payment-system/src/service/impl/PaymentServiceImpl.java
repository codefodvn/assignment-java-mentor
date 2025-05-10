/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.*;
import service.PaymentService;
import view.Input;

/**
 *
 * @author macos
 */
public class PaymentServiceImpl implements PaymentService<User>{
    private UserServiceImpl usv = new UserServiceImpl();
    private TransactionServiceImpl trs = new TransactionServiceImpl();
    @Override
    public boolean payByCard(User usr,double ammount) {
        double money;
        double creditBalance = usr.getCredit().getCreditBalance();
        double creditLimit = usr.getCredit().getCreditLimit();
        double balance = usr.getCredit().getBalance();
        if(creditLimit>creditBalance){
            double needToPay = creditLimit - creditBalance;
            if(balance<needToPay){
                System.out.println("Your balance is not enough to pay all credit balance!");
                return false;
            }else{
                balance -= needToPay;
                creditBalance = creditLimit;
            }
            usr.setCredit(new CreditCard(creditLimit,creditLimit,balance));
        }
        money = balance + creditLimit;
        if(money<ammount){
            Transaction trans = new Transaction(trs.getRandomID(),usr.getUserID(),ammount,"CreditCard",LocalDateTime.now(),"FAIL");
            trs.add(trans);
            System.out.println("Payment fail!");
            System.out.println(trans.toString());
            System.out.println();
            return false;
        }
        if(balance > ammount){
            balance -= ammount;
        }else{
            ammount -= balance;
            balance = 0;
            creditBalance -= ammount;
        }
        usr.setCredit(new CreditCard(creditLimit,creditBalance,balance));
        
        Transaction trans = new Transaction(trs.getRandomID(),usr.getUserID(),ammount,"CreditCard",LocalDateTime.now(),"SUCCESS");
        trs.add(trans);
        System.out.println("Payment successfully!");
        System.out.println(trans.toString());
        System.out.println();
        usv.update(usr);
        return true;
    }

    @Override
    public boolean payByBank(User usr,double ammount) {
        double remain;
        if(usr.getBank().getBalance()<ammount){
            Transaction trans = new Transaction(trs.getRandomID(),usr.getUserID(),ammount,"BankTransfer",LocalDateTime.now(),"FAIL");
            trs.add(trans);
            System.out.println("Payment fail!");
            System.out.println();
            return false;
        }
        if(ammount <2000){
            remain = usr.getBank().getBalance()-ammount;
        }else{
            ammount *=usr.getBank().getFee();
            remain = usr.getBank().getBalance()-ammount;
        }
        usr.setBank(new BankTransfer(remain));
        usv.update(usr);
        Transaction trans = new Transaction(trs.getRandomID(),usr.getUserID(),ammount,"BankTransfer",LocalDateTime.now(),"SUCCESS");
        trs.add(trans);
        System.out.println("Payment successfully!");
        System.out.println(trans.toString());
        System.out.println();
        return true;
    }

    @Override
    public boolean payByEWallet(User usr,double ammount) {
        double limit = usr.getWallet().getLimit();
        double balance = usr.getWallet().getBalance();
        double dailySpent = usr.getWallet().getDailySpent();
        LocalDate last = LocalDate.now();
        
        if(ammount <= limit-dailySpent )
            if(ammount <= balance){
                dailySpent += ammount;
                balance -= ammount;
                usr.setWallet(new EWallet(dailySpent,last,balance));
                usv.update(usr);
                Transaction trans = new Transaction(trs.getRandomID(),usr.getUserID(),ammount,"E-Wallet",LocalDateTime.now(),"SUCCESS");
                trs.add(trans);
                System.out.println("Payment successfully!");
                System.out.println(trans.toString());
                System.out.println();
                return true;
            }
        Transaction trans = new Transaction(trs.getRandomID(),usr.getUserID(),ammount,"E-Wallet",LocalDateTime.now(),"FAIL");
        trs.add(trans);
        System.out.println("Payment fail, please check your daily spent!");
        System.out.println(trans.toString());
        System.out.println();
        return false;
            
    }
    
    public void payByDivide(User usr,double ammount){
        
        if(!Input.continueConfirm("Do you want pay by divide ? ")) return;
        
        double bankBalance = usr.getBank().getBalance()/1.02;
        double ewlBalance = usr.getWallet().getBalance();
        if(usr.getWallet().getLimit()-usr.getWallet().getDailySpent()<ewlBalance)
            ewlBalance = usr.getWallet().getLimit()-usr.getWallet().getDailySpent();
        double cardBalance = usr.getCredit().getBalance()+usr.getCredit().getCreditLimit() - (usr.getCredit().getCreditLimit() -  usr.getCredit().getCreditBalance());
        
        if(cardBalance < usr.getCredit().getCreditLimit())
            cardBalance = 0;
        
        if(bankBalance+ewlBalance+cardBalance < ammount){
            System.out.println("Not enoungh balance!");
            return;
        }
        
        if(bankBalance<ammount && bankBalance >0) payByBank(usr,bankBalance);
        else{ 
            payByBank(usr,ammount);
            return;
        }
        ammount -= usr.getBank().getBalance();
        
        if(ewlBalance <ammount)
                payByEWallet(usr,ewlBalance);
        else{
            payByEWallet(usr,ammount);
            System.out.println("Pay "+ ammount+ " by E-Wallet");
            return;
        }
        ammount -= ewlBalance;
        
        payByCard(usr,ammount);
    }
    
}
