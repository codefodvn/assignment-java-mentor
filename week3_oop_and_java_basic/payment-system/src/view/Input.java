package view;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getValue(String msg) {
        System.out.print(msg);
        return trimName(scanner.nextLine());
    }

    public static String getAndValidValue(String msg, String regex, String cause) {
        return getValidInput(msg, input -> input.matches(regex), cause);
    }

    public static double getAndValidNumber(String msg) {
    while (true) {
        try {
            String value = getValue(msg);  
            double money = Double.parseDouble(value); 
            if (money >= 0) {
                return money; 
            } else {
                System.out.println("This must be non-negative.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid number.");
        }
    }
}


    private static <T> T getValidInput(String msg, java.util.function.Predicate<String> condition, String cause) {
        while (true) {
            String value = getValue(msg);
            if (condition.test(value)) {
                return (T) value;
            } else {
                System.out.println("Invalid input. " + cause);
            }
        }
    }

    public static String standardlizeName(String name) {
        if (name == null || name.isEmpty()) return name;
        String[] words = name.trim().split("\\s+");
        StringBuilder standardized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                standardized.append(Character.toUpperCase(word.charAt(0)))
                            .append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return standardized.toString().trim();
    }

    public static String getAndValidateName(String msg) {
        String name = getAndValidValue("Enter " + msg + " name: ", "^[A-Za-z]+( [A-Za-z]+)*$", msg + " can only contain alphabetic characters and spaces.");
        return standardlizeName(name);
    }
    public static String getAndValidateString(String msg) {
        return getAndValidValue("Enter " + msg + " : ", "^[A-Za-z]+( [A-Za-z]+)*$", msg + " can only contain alphabetic characters and spaces.");
    }
    public static String getValidID(String msg1, String msg2) {
        String idd = getValidInput(msg1 + " ID: ", id -> id.matches(msg2+ "-\\d{4}") || id.matches(msg2.toLowerCase()+ "-\\d{4}"), "Invalid ID format, please use: " + msg2 + "-XXXX");
        String split[] = idd.split("-");
        return split[0].toUpperCase()+"-"+split[1];
        
    }

    public static String getAndValidPhoneNumber(String msg1) {
        return getValidInput(msg1 + " phone: ", phone -> phone.matches("0\\d{9}"), "Phone number must have 10 digits and start with 0.");
    }

    public static String getAndValidEmail(String msg) {
        return getValidInput(msg + " email: ", email -> email.matches("[\\w-]+@[\\w-]+\\.[a-z]{2,4}"), "Please enter a valid email.");
    }

    public static String getAndValidGender(){
        String gender = getValue("Enter m for Male or f for Female: ");
        while(true){
            if((!gender.toUpperCase().equals("M")) && (!gender.toUpperCase().equals("F"))){
                gender = getValue("Enter m for Male or f for Female: ");
            }
            if(gender.toUpperCase().equals("M"))return "Male";
            return "Female";
        }
        
    }

    public static LocalDate getAndValidDay(String msg1, int minAge) {
        String dateStr = getValue("Enter " + msg1 + " : ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                LocalDate birthDate = LocalDate.parse(dateStr, dtf);
                if (Period.between(birthDate, LocalDate.now()).getYears() >= minAge) {
                    return birthDate;
                } else {
                    System.out.println(msg1 + " must be at least " + minAge + " years old.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please follow DD/MM/YYYY.");
            }
        }
    }
    public static LocalDate getAndValidBirthDay(String msg1, int minAge) {
        return getAndValidDay(msg1+" birthday (DD/MM/YYYY)",minAge);
    }
    public static String getQualification() {
        return getChoiceFromMenu("Choose qualification: ", new String[]{"Intermediate", "College", "University", "Postgraduate"});
    }

    public static String getPosition() {
        return getChoiceFromMenu("Choose position: ", new String[]{"Director", "Manager", "Supervisor", "Specialist", "Waiter", "Receptionist"});
    }

    public static String getCustomerType() {
        return getChoiceFromMenu("Choose customer type: ", new String[]{"Diamond", "Platinum", "Gold", "Silver", "Member"});
    }

    private static String getChoiceFromMenu(String prompt, String[] options) {
        class Submenu extends Menu {
            String choice;

            public String getChoice() {
                return choice;
            }

            public Submenu() {
                super(prompt, options);
            }

            @Override
            public void execute(int choice) {
                this.choice = options[choice - 1];
                this.stop();
            }
        }
        Submenu submenu = new Submenu();
        submenu.run();
        return submenu.getChoice();
    }

    public static String trimName(String name) {
        return name.trim().replaceAll("\\s+", " ");
    }
    public static String getAndValidIDCard(String msg1) {
        String idc = getValue("Enter " + msg1 + " ID Card Number: ");
        while (true) {
            if (isValidNumber(idc)) {
                if ((idc.length() != 9) && (idc.length() != 12)) {
                    idc = getValue("ID Card Number must have 9 or 12 digits: ");
                    continue;
                }
            } else {
                idc = getValue("ID Card Number must be a legit number: ");
                continue;
            }
            return idc;
        }
    }

    private static boolean isValidNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean continueConfirm(String msg) {
        while (true) {
            String value = getValue(msg + " (Y/N):").toLowerCase();
            if (value.equals("y")) {
                return true;
            } else if (value.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter 'Y' or 'N'.");
            }
        }
    }
    public static String getAndValidPassword(String msg) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return getValidInput(msg+" password: ", 
                         password -> password.matches(passwordPattern), 
                         "Password must be at least 8 characters long, include uppercase, lowercase, a number, and a special character.");
    }
    public static String getAndValidUserName(String msg) {
    String usernamePattern = "^[A-Za-z][A-Za-z0-9_.]{3,19}$";
    return getValidInput(msg + " username: ", 
                         username -> username.matches(usernamePattern), 
                         "Username must start with a letter, be 4-20 characters long, and only contain letters, numbers, underscores (_), or dots (.)");
    }


}
