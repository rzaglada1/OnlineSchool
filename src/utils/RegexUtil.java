package utils;

import exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static final String REGEX_FIRST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_LAST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_PHONE = "^\\+\\d{12}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@.+\\..+";
    public static final String REGEX_IP_ADDRESS = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";


    public boolean isCorrect(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public String[] personAttribute() throws ValidationException {

        String firstName = "";
        String lastName = "";
        String phone = "";
        String email = "";

        System.out.println("Enter firstname (for example - \"Олена\")");
        firstName = new MenuUtils().inputString();
        while (!isCorrect(firstName, REGEX_FIRST_NAME)) {
            System.out.println("Be careful when typing");
            firstName = new MenuUtils().inputString();
        }
        System.out.println("Enter lastname (for example - \"Полякова\")");
        lastName = new MenuUtils().inputString();
        while (!isCorrect(lastName, REGEX_LAST_NAME)) {
            System.out.println("Be careful when typing");
            lastName = new MenuUtils().inputString();
        }

        System.out.println("Enter phone (for example \"+380500000000\")");
        phone = new MenuUtils().inputString();
        while (!isCorrect(phone, REGEX_PHONE)) {
            System.out.println("Be careful when typing");
            phone = new MenuUtils().inputString();
        }

        System.out.println("Enter email (for example \"AnnaPolyakova@ukr.net\")");
        email = new MenuUtils().inputString();
        while (!isCorrect(email, REGEX_EMAIL)) {
            System.out.println("Be careful when typing");
            email = new MenuUtils().inputString();
        }

        return new String[]{firstName, lastName, phone, email};
    }

}
