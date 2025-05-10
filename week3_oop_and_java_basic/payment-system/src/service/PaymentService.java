/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author macos
 */
public interface PaymentService<T> {
    boolean payByCard(T entity,double ammount);
    boolean payByBank(T entity,double ammount);
    boolean payByEWallet(T entity,double ammount);
}