/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constance.UserStatus;

/**
 *
 * @author macos
 */
public class User {
    private String userName,password,userID;
    private UserStatus status;
    private BankTransfer bank;
    private CreditCard credit;
    private EWallet wallet;
    
    public User(String userName, String password, String userID, UserStatus status, BankTransfer bank, CreditCard credit, EWallet wallet) {
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        this.status = status;
        this.bank = bank;
        this.credit = credit;
        this.wallet = wallet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public BankTransfer getBank() {
        return bank;
    }

    public void setBank(BankTransfer bank) {
        this.bank = bank;
    }

    public CreditCard getCredit() {
        return credit;
    }

    public void setCredit(CreditCard credit) {
        this.credit = credit;
    }

    public EWallet getWallet() {
        return wallet;
    }

    public void setWallet(EWallet wallet) {
        this.wallet = wallet;
    }

    public String toCSV() {
        return userName + "," + password + "," + userID + "," + status.getCurrentStatus() + "," + bank.toCSV() + "," + credit.toCSV() + "," + wallet.toCSV();
    }
   
    
    
}
