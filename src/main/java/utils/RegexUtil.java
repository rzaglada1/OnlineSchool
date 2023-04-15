package utils;

import exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import services.PersonService;
import utils.log.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    String nameLog = "Log OnlineSchool";
    public static final String REGEX_FIRST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_LAST_NAME = "^[А-ЯІЇ][а-яії']+$";
    public static final String REGEX_PHONE = "^\\+\\d{12}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@.+\\..+";
    public static final String REGEX_IP_ADDRESS = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

    private MenuUtils menuUtils;
    private PersonService personService;

    @Autowired
    public void setMenuUtils(MenuUtils menuUtils) {
        this.menuUtils = menuUtils;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
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
        firstName = menuUtils.inputString();
        while (!isCorrect(firstName, REGEX_FIRST_NAME)) {
            System.out.println("Be careful when typing");
            firstName = menuUtils.inputString();
        }
        System.out.println("Enter lastname (for example - \"Полякова\")");
        lastName = menuUtils.inputString();
        while (!isCorrect(lastName, REGEX_LAST_NAME)) {
            System.out.println("Be careful when typing");
            lastName = menuUtils.inputString();
        }

        System.out.println("Enter phone (for example \"+380500000000\")");
        phone = menuUtils.inputString();
        while (!isCorrect(phone, REGEX_PHONE)) {
            System.out.println("Be careful when typing");
            phone = menuUtils.inputString();
        }

        System.out.println("Enter email (for example \"AnnaPolyakova@ukr.net\")");
        email = menuUtils.inputString();
        while (!isCorrect(email, REGEX_EMAIL)) {
            System.out.println("Be careful when typing");
            email = menuUtils.inputString();
        }

        if (personService.checkDoubleEmail(email)) {
            Log.info(nameLog, "Warning: this email is present in another person");
        }
        return new String[]{firstName, lastName, phone, email};
    }


}
