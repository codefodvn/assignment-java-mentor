package com.huynq.multipaysystem.model;


import com.huynq.multipaysystem.constance.PaymentType;

import java.math.BigDecimal;

public class CreditCard extends PaymentMethod {
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private BigDecimal creditLimit;
    private BigDecimal outstandingBalance;

    public CreditCard(String id, String name, PaymentType type, String cardNumber, String expirationDate, String cvv, double creditLimit, double outstandingBalance) {
        super(id, name, PaymentType.CREDIT_CARD);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.creditLimit = BigDecimal.valueOf(creditLimit);
        this.outstandingBalance = BigDecimal.valueOf(0.0);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = BigDecimal.valueOf(creditLimit);
    }

    public BigDecimal getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalanceBalance(double outstandingBalance) {
        this.outstandingBalance = BigDecimal.valueOf(outstandingBalance);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvv='" + cvv + '\'' +
                ", creditLimit=" + creditLimit +
                ", outstandingBalance=" + outstandingBalance +
                '}';
    }
}
