/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import model.Transaction;
import model.User;
import service.TransactionService;

/**
 *
 * @author macos
 */
public class TransactionServiceImpl implements TransactionService<Transaction> {
    
    private static final String FILE_PATH = "transaction.txt";

    @Override
    public ArrayList<Transaction> getAll(String usrID){
        ArrayList<Transaction> trans = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data[1].equals(usrID) || usrID.equals("all")){ 
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(data[4], formatter);
                    trans.add(new Transaction(data[0],data[1],Double.parseDouble(data[2]),data[3],dateTime,data[5]));
                }
                
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public void add(Transaction transaction) {
        ArrayList<Transaction> transs = getAll("all");
        transs.add(transaction);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Transaction tran : transs) {
                bw.write(tran.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public String getRandomID(){
        Random random = new Random();
        double id = (double)100000 + random.nextInt(900000);
        return String.valueOf(id);
    }
    public void display(){}
}
