
import configuration.SpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import utils.*;
import utils.log.Log;

public class Main {


    public static void main(String[] args) {


        String nameLog = "Log OnlineSchool";

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        MenuUtils menuUtils = context.getBean("menuUtils", MenuUtils.class);


        new Thread(new WatchDir(MenuUtils.STR_PATH_DIRECTORY, MenuUtils.STR_NAME_SERVICE)).start();
        new Thread(new WatchDir(MenuUtils.STR_PATH_DIRECTORY, MenuUtils.STR_NAME_BLACK_LIST)).start();


        menuUtils.createObjects();


        int item;
        while ((item = menuUtils.checkCorrect()) != 0) {
            switch (item) {
                case 1 -> menuUtils.case1();
                case 2 -> menuUtils.case2();
                case 3 -> menuUtils.case3();
                case 4 -> menuUtils.case4();
                case 5 -> menuUtils.case5();
                case 6 -> menuUtils.case6();
                case 7 -> menuUtils.case7();
                case 8 -> menuUtils.case8();
                case 9 -> menuUtils.case9();
                case 10 -> menuUtils.case10();
                case 11 -> menuUtils.case11();
                case 12 -> menuUtils.case12();
                case 13 -> menuUtils.case13();
                case 14 -> menuUtils.case14();
                case 15 -> menuUtils.case15();
                case 16 -> menuUtils.case16();
                case 17 -> menuUtils.case17();
                case 18 -> menuUtils.case18();
                case 19 -> menuUtils.case19();
                case 20 -> menuUtils.case20();
                case 21 -> menuUtils.case21();
                case 22 -> menuUtils.case22();
                case 23 -> menuUtils.case23();
                case 24 -> menuUtils.case24();
                case 25 -> menuUtils.case25();
                case 26 -> menuUtils.case26();
                default -> System.out.println("Try again...");
            }
        }


        Log.info(nameLog, "Exiting program ...");
    }


}


