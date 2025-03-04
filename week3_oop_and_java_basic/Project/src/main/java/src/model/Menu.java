package src.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {
    public Menu() {
        super();
    }
    public int getUserChoice(int k){
        if(k==0) {
            System.out.printf("Không có gì hết ^_^");
            return 0;
        }
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i+1)+"-"+this.get(i));
        }
        System.out.println("________________________");
        do {
            System.out.printf("Chọn 1..%d: \n",k);
            try {
                choice = Integer.parseInt(sc.nextLine());
                if(choice<1 || choice>8) System.out.printf("**Số từ 1 đến %d \n",k);
            } catch (Exception e) {
                System.out.println("**Number format");
            }
        } while (choice < 1 || choice > 9);
        return choice;
    }

}
