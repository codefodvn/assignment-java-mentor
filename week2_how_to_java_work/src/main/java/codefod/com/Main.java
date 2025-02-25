package codefod.com;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        getNumWords("Vo quang minh");
    }

//    java -Xms512m -Xmx1024m -Xss1m -XX:+UseG1GC -XX:+PrintGCDetails codefod.com.Main
//    javac Main.java tao file bytecode tu class
//    java Main.java chay chuong trinh
    public static void getNumWords(String str){
        List<String> result = Arrays.asList(str.split("\\s+"));
        for (String s : result) {
            System.out.println(s);
        }
    }
}