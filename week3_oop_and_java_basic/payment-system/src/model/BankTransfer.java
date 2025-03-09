/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author macos
 */
public class BankTransfer extends PaymentMethod {
    private  final double TRANSACTION_FEE = 1.02;
    
    public BankTransfer(double balance) {
        super(balance);
    }  
    public double getFee(){
        return TRANSACTION_FEE;
    }

    public String toCSV() {
        return super.toString();
    }

    @Override
    public String toString() {
        return  "( TRANSACTION_FEE = " + TRANSACTION_FEE + ", balance = "+super.balance+ " )";
    }
    
    
    
}
