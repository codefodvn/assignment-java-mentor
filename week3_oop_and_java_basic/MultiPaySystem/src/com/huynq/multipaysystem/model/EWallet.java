package com.huynq.multipaysystem.model;


import java.math.BigDecimal;
import com.huynq.multipaysystem.constance.PaymentType;

import java.math.BigDecimal;

    public class EWallet extends PaymentMethod {
        private String walletId;
        private BigDecimal balance;
        private BigDecimal dailySpent;
        private BigDecimal dailyLimit;


        public EWallet(String id, String name, String walletId, BigDecimal balance, BigDecimal dailySpent) {
            super(id, name, PaymentType.E_WALLET);
            this.walletId = walletId;
            this.balance = BigDecimal.ZERO;
            this.dailySpent =  BigDecimal.ZERO;
            this.dailyLimit = new BigDecimal("5000");
        }

        // Getter và Setter
        public String getWalletId() {
            return walletId;
        }

        public void setWalletId(String walletId) {
            this.walletId = walletId;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public BigDecimal getDailySpent() {
            return dailySpent;
        }

        public void setDailySpent(BigDecimal dailySpent) {
            this.dailySpent = dailySpent;
        }

        public BigDecimal getDailyLimit() {
            return dailyLimit;
        }

        public void setDailyLimit(BigDecimal dailyLimit) {
            this.dailyLimit = dailyLimit;
        }
    }

