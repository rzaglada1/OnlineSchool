
import repositories.*;
import utils.*;
import utils.log.Log;

public class Main {

    public static void main(String[] args) {

        String nameLog = "Log OnlineSchool";
        MenuUtils menuUtils = new MenuUtils();

        CourseRepository courseRepository = CourseRepository.getInstance();
        LectureRepository lectureRepository = LectureRepository.getInstance();
        PersonRepository personRepository = PersonRepository.getInstance();
        AddMaterialsRepository addMaterialsRepository = AddMaterialsRepository.getInstance();
        HomeWorkRepository homeworkRepository = HomeWorkRepository.getInstance();

        menuUtils.createObjects(addMaterialsRepository, courseRepository
                , lectureRepository, personRepository);


        System.out.println('\n');
        Log.info(nameLog, "=============== Homework 21 =====================");



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
                default -> System.out.println("Try again...");
            }
        }
        Log.info(nameLog, "Exiting program ...");
    }
}


