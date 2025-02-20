package codefod.com;

public class Main {

    public static void main(String[] args) {
        StringBuilder string = new StringBuilder();
        for(int i =0; i< 10;i++){
            string.append(i+" ");
        }
        System.out.println(string);
    }

//    java -Xms512m -Xmx1024m -Xss1m -XX:+UseG1GC -XX:+PrintGCDetails codefod.com.Main
//    javac Main.java tao file bytecode tu class
//    java Main.java chay chuong trinh
}