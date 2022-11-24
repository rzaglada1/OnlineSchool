package utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuUtils {

    public static final String REGEX_FIRST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_LAST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_PHONE = "^\\+\\d{12}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@.+\\..+";

    public int checkCorrect() {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstRun = false;
        int category = -1;
        while (category > 8 || category < 0) {
            if (isFirstRun) {
                System.out.println("Something went wrong ... Try again. ");
            }
            isFirstRun = true;

            System.out.println("");
            System.out.println("Select category:");
            System.out.println("0 - exit program");
            System.out.println("1 - Objects course");
            System.out.println("2 - Objects lecture");
            System.out.println("3 - Creating course");
            System.out.println("4 - Creating lecture");
            System.out.println("5 - Creating teacher and adding in lecture");
            System.out.println("6 - Creating student");
            System.out.println("7 - Open Lecture by ID");
            System.out.println("8 - Delete Lecture by ID");

            if (scanner.hasNextInt()) {
                category = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        return category;
    }

    public String inputString() {
        return new Scanner(System.in).next();
    }


    public boolean isCorrect(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public String[] personAttribute() {

        String firstName = "";
        String lastName = "";
        String phone = "";
        String email = "";

        System.out.println("Enter firstname (for example - \"Олена\")");
        firstName = inputString();
        while (!isCorrect(firstName, REGEX_FIRST_NAME)) {
            System.out.println("Be careful when typing");
            firstName = inputString();
        }
        System.out.println("Enter lastname (for example - \"Полякова\")");
        lastName = inputString();
        while (!isCorrect(lastName, REGEX_LAST_NAME)) {
            System.out.println("Be careful when typing");
            lastName = inputString();
        }

        System.out.println("Enter phone (for example \"+380500000000\")");
        phone = inputString();
        while (!isCorrect(phone, REGEX_PHONE)) {
            System.out.println("Be careful when typing");
            phone = inputString();
        }

        System.out.println("Enter email (for example \"AnnaPolyakova@ukr.net\")");
        email = inputString();
        while (!isCorrect(email, REGEX_EMAIL)) {
            System.out.println("Be careful when typing");
            email = inputString();
        }

        return new String[]{firstName, lastName, phone, email};
    }

    public int inputDigit() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter digit ");
                scanner.next();
            }
        }
    }
}
