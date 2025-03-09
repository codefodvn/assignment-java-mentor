/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;

/**
 *
 * @author macos
 */
public interface TransactionService<T> {
    ArrayList<T> getAll(String usrID);
    void add(T entity);
    
    
}
