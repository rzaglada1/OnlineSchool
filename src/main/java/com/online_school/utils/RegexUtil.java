package com.online_school.utils;

import com.online_school.exceptions.ValidationException;
import com.online_school.utils.log.Log;
import com.online_school.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexUtil {

    String nameLog = "Log OnlineSchool";
    public static final String REGEX_FIRST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_LAST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_PHONE = "^\\+\\d{12}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@.+\\..+";
    public static final String REGEX_IP_ADDRESS = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";


    private final PersonService personService;

    @Autowired
    public RegexUtil(PersonService personService) {
        this.personService = personService;
    }

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

        if (personService.checkDoubleEmail(email)) {
            Log.info(nameLog, "Warning: this email is present in another person");
        }
        return new String[]{firstName, lastName, phone, email};
    }

    private String inputString() {
        return new Scanner(System.in).next();
    }

}
