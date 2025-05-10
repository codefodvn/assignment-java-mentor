/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author macos
 */
public class EWallet extends PaymentMethod {
    private final double DAYLY_LIMIT = 5000;
    private double dailySpent;
    private LocalDate lastTransaction;

    public EWallet(double dailySpent, LocalDate lastTransaction, double balance) {
        super(balance);
        this.dailySpent = dailySpent;
        this.lastTransaction = lastTransaction;
    }

    public double getDailySpent() {
        return dailySpent;
    }

    public void setDailySpent(double dailySpent) {
        this.dailySpent = dailySpent;
    }

    public LocalDate getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(LocalDate lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public double getLimit(){
        return this.DAYLY_LIMIT;
    }

    public String toCSV() {
        return dailySpent + "," + lastTransaction+","+super.toString() ;
    }

    @Override
    public String toString() {
        return "( DAYLY_LIMIT = " + DAYLY_LIMIT + ", dailySpent = " + dailySpent + ", lastTransaction=" + lastTransaction +  ", balance = "+super.balance+" )";
    } 
    
    
}
