package view;

import java.util.ArrayList;
import java.util.Scanner;




public abstract class Menu<T>{
    protected boolean running = true;
    protected String title;
    protected ArrayList<T> choiceList;
    public Menu(String search_Store, Menu searchMenu){}
    public Menu(String title, String[] mc){
        this.title=title;
        choiceList= new ArrayList<>();
        for(String s:mc) choiceList.add((T) s);
    }
//-------------------------------------------
    public void display(){
        System.out.println("");
        System.out.println(title);
        System.out.println("--------------------------------");
        for(int i=0; i<choiceList.size();i++)
            System.out.println((i+1)+"."+choiceList.get(i));
        System.out.println("--------------------------------");
    }
//-------------------------------------------
    public int getSelected(){
        display();
        return (int)(Input.getAndValidNumber(""));
    }
//-------------------------------------------
    public abstract void execute(int n);
//-------------------------------------------
    public void stop() {
        running = false;
    }
    public void run(){
        while(running){
            int n=getSelected();
            execute(n);
            if(n==choiceList.size()) {break;} 
            if(n>choiceList.size()){
                System.out.println("Choice must be <= " + choiceList.size());
                continue;
            }
        }
    }
    
        
    //-------------------------------------------    
}