package com.huynq.multipaysystem.model;


import com.huynq.multipaysystem.constance.PaymentType;

import java.math.BigDecimal;

public class BankTransfer extends PaymentMethod  {
    private String bankName;
    private String accountNumber;
    private boolean isAccountActive;
    private BigDecimal balance;

    public BankTransfer(String id, String name, PaymentType type, String bankName, String accountNumber, boolean isAccountActive, BigDecimal balance) {
        super(id, name, type);
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.isAccountActive = isAccountActive;
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isAccountActive() {
        return isAccountActive;
    }

    public void setAccountActive(boolean accountActive) {
        isAccountActive = accountActive;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankTransfer{" +
                "bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", isAccountActive=" + isAccountActive +
                ", balance=" + balance +
                '}';
    }
}


