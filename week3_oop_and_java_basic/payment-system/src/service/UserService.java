/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author macos
 */
public interface UserService<T> {
    void update(T entity);
    void changePassword(T entity);
    void addNewPayment();
    void getUserStatus(T entitty);
}
