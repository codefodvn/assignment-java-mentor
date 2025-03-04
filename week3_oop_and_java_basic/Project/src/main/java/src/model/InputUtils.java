package src.model;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int inputId(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                if (input.matches("\\d{8}")) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println("Vui lòng nhập đúng 8 chữ số!");
                }
            }catch(NumberFormatException e){
                System.out.println("Vui lòng nhập số nguyên!");
            }
        }
    }

    public static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên!");
            }
        }
    }

    public static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực!");
            }
        }
    }

    public static boolean inputBoolean(String message) {
        System.out.print(message + " (true/false): ");
        return Boolean.parseBoolean(scanner.nextLine());
    }

    public static void closeScanner() {
        scanner.close();
    }
}
