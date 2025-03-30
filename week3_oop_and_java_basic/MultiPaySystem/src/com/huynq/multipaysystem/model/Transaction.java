package com.huynq.multipaysystem.model;


import com.huynq.multipaysystem.constance.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String userId;
    private String paymentMethodId;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime timestamp;
    private boolean isInternational;
    private BigDecimal fee;


    public Transaction(String id, String userId, String paymentMethodId, BigDecimal amount, boolean isInternational) {
        this.id = id;
        this.userId = userId;
        this.paymentMethodId = paymentMethodId;
        this.amount =  BigDecimal.ZERO;
        this.status = TransactionStatus.PENDING;
        this.timestamp = LocalDateTime.now();
        this.isInternational = isInternational;
        this.fee = BigDecimal.ZERO;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}