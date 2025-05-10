/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macos
 */
public class CreditCard extends PaymentMethod{
    private double creditLimit;
    private double creditBalance;

    public CreditCard(double creditLimit,double creditBalance, double balance) {
        super(balance);
        this.creditLimit = creditLimit;
        this.creditBalance = creditBalance;
    }
    
    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }


    public String toCSV() {
        return creditLimit + "," + creditBalance + ","+super.toString();
    }

    @Override
    public String toString() {
        return  "( creditLimit = " + creditLimit + ", creditBalance = " + creditBalance + ", balance = "+super.balance+" )";
    }
    
    
    
}
