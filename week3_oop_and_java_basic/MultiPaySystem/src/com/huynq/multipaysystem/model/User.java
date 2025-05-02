package com.huynq.multipaysystem.model;


import com.huynq.multipaysystem.constance.UserStatus;

import java.util.ArrayList;

public class User {
    private String userName;
    private String passWord;
    private UserStatus status;
    private ArrayList<PaymentMethod> paymentMethods;
    private ArrayList<Transaction> transactions;
    private boolean isBusinessAccount;
    private int failedLoginAttempts;
    private int failedOTPAttempts;


    public User() {
    }

    public User(String userName, String passWord, UserStatus status, ArrayList<PaymentMethod> paymentMethods, ArrayList<Transaction> transactions, boolean isBusinessAccount, int failedLoginAttempts, int failedOTPAttempts) {
        this.userName = userName;
        this.passWord = passWord;
        this.status = UserStatus.ACTIVE;
        this.paymentMethods = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.isBusinessAccount = isBusinessAccount;
        this.failedLoginAttempts = 0;
        this.failedOTPAttempts = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean isBusinessAccount() {
        return isBusinessAccount;
    }

    public void setBusinessAccount(boolean businessAccount) {
        isBusinessAccount = businessAccount;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public int getFailedOTPAttempts() {
        return failedOTPAttempts;
    }

    public void setFailedOTPAttempts(int failedOTPAttempts) {
        this.failedOTPAttempts = failedOTPAttempts;
    }

}