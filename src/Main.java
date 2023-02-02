
import repositories.*;
import server_client.StorageBlackList;
import utils.*;
import utils.log.Log;
import utils.log.LogToFile;

public class Main {

    public static void main(String[] args) {

        String nameLog = "Log OnlineSchool";
        MenuUtils menuUtils = new MenuUtils();

        new Thread(new WatchDir(LogToFile.STR_PATH_SERVICE, LogToFile.STR_NAME_SERVICE)).start();
        new Thread(new WatchDir(StorageBlackList.STR_DIR_BLACK_LIST, StorageBlackList.STR_NAME_BLACK_LIST)).start();

        CourseRepository courseRepository = CourseRepository.getInstance();
        LectureRepository lectureRepository = LectureRepository.getInstance();
        PersonRepository personRepository = PersonRepository.getInstance();
        AddMaterialsRepository addMaterialsRepository = AddMaterialsRepository.getInstance();
        HomeWorkRepository homeworkRepository = HomeWorkRepository.getInstance();

        menuUtils.createObjects(addMaterialsRepository, courseRepository
                , lectureRepository, personRepository, homeworkRepository);


        System.out.println('\n');
        Log.info(nameLog, "=============== Homework 25 =====================");


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
                default -> System.out.println("Try again...");
            }
        }
        Log.info(nameLog, "Exiting program ...");
    }
}


