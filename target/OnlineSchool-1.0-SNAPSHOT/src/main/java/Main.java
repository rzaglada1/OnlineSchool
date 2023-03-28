
import repositories.*;
import utils.*;
import utils.log.Log;

public class Main {



    public static void main(String[] args) {


        String nameLog = "Log OnlineSchool";
        MenuUtils menuUtils = new MenuUtils();



        new Thread(new WatchDir(MenuUtils.STR_PATH_DIRECTORY, MenuUtils.STR_NAME_SERVICE)).start();
        new Thread(new WatchDir(MenuUtils.STR_PATH_DIRECTORY, MenuUtils.STR_NAME_BLACK_LIST)).start();

        CourseRepository courseRepository = CourseRepository.getInstance();
        LectureRepository lectureRepository = LectureRepository.getInstance();
        PersonRepository personRepository = PersonRepository.getInstance();
        AddMaterialsRepository addMaterialsRepository = AddMaterialsRepository.getInstance();
        HomeWorkRepository homeworkRepository = HomeWorkRepository.getInstance();

        menuUtils.createObjects(addMaterialsRepository, courseRepository
                , lectureRepository, personRepository, homeworkRepository);




            int item;
            while ((item = menuUtils.checkCorrect()) != 0) {
                switch (item) {
                    case 1 -> menuUtils.case1(courseRepository);
                    case 2 -> menuUtils.case2(lectureRepository);
                    case 3 -> menuUtils.case3(personRepository);
                    case 4 -> menuUtils.case4(personRepository);
                    case 5 -> menuUtils.case5(homeworkRepository, lectureRepository);
                    case 6 -> menuUtils.case6(addMaterialsRepository, lectureRepository);
                    case 7 -> menuUtils.case7(homeworkRepository, addMaterialsRepository, lectureRepository);
                    case 8 -> menuUtils.case8(courseRepository);
                    case 9 -> menuUtils.case9(personRepository);
                    case 10 -> menuUtils.case10();
                    case 11 -> menuUtils.case11();
                    case 12 -> menuUtils.case12();
                    case 13 -> menuUtils.case13();
                    case 14 -> menuUtils.case14();
                    case 15 -> menuUtils.case15();
                    case 16 -> menuUtils.case16();
                    case 17 -> menuUtils.case17(addMaterialsRepository);
                    case 18 -> menuUtils.case18(lectureRepository);
                    case 19 -> menuUtils.case19(personRepository);
                    case 20 -> menuUtils.case20();
                    case 21 -> menuUtils.case21();
                    case 22 -> menuUtils.case22();
                    case 23 -> menuUtils.case23(lectureRepository);
                    case 24 -> menuUtils.case24(addMaterialsRepository);
                    case 25 -> menuUtils.case25(personRepository);
                    case 26 -> menuUtils.case26(personRepository);
                    default -> System.out.println("Try again...");
                }
            }


        Log.info(nameLog, "Exiting program ...");
    }


}


