

import models.*;
import repositories.*;
import services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //  Homework N9 .
        System.out.println("");
        System.out.println("========= Homework N11  ================");
        System.out.println("");

        CourseService courseService = new CourseService();
        CourseRepository courseRepository = new CourseRepository();

        LectureService lectureService = new LectureService();
        LectureRepository lectureRepository = new LectureRepository();

        PersonService personService = new PersonService();
        PersonRepository personRepository = new PersonRepository();

        HomeworkService homeworkService = new HomeworkService();
        HomeworkRepository homeworkRepository = new HomeworkRepository();

        AddTaskService addTaskService = new AddTaskService();
        AddTaskRepository addTaskRepository = new AddTaskRepository();


        // creating Course
        courseRepository.add(courseService.createCurse("Java course"));

        // creating three Lectures
        int idCourse = courseRepository.getAll()[0].getID();
        System.out.println("idCourse  " + idCourse);

        for (int i = 0; i < 3; i++) {
            lectureRepository.add(lectureService.createLecture("Lecture " + (i + 1), courseRepository.getAll()[0]));
        }

        System.out.println("Created " + Lecture.getCreateCount() + " lecture objects ");

        // printing repository objects
        lectureService.printObjectsRepository(lectureRepository);

        //===============================


        //================================


        while (true) {

            int category = checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    System.out.println("");
                    // printing repository objects
                    courseService.printObjectsRepository(courseRepository);
                    System.out.println("================================");
                    System.out.println(Course.getCreateCount() + " Course objects created since beginning");
                    System.out.println("Total " + courseRepository.objectsTotal() + " Course objects");
                    break;

                case 2:
                    System.out.println("Selected   - \"2 - Objects lecture\" ");
                    System.out.println("");
                    // printing repository objects
                    for (Model lecture: lectureRepository.getAll()) {
                        if (lecture != null) {
                            System.out.println(lecture);
                        }
                    }

                    System.out.println("================================");
                    System.out.println(Lecture.getCreateCount() + " Lecture objects created since beginning");
                    System.out.println("Total " + lectureRepository.objectsTotal() + " Lecture objects");
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    courseRepository.add(courseService.createCurse(nameObject()));
                    System.out.println("================================");
                    System.out.println(Course.getCreateCount() + " Course objects created since beginning");
                    System.out.println("Total " + courseRepository.objectsTotal() + " Course objects");
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    lectureRepository.add(lectureService.createLecture(nameObject()));
                    System.out.println("================================");
                    System.out.println(Lecture.getCreateCount() + " Lecture objects created since beginning");
                    System.out.println("Total " + lectureRepository.objectsTotal() + " Lecture objects");
                    break;

                case 5:
                    System.out.println("Selected   - \"5 - Creating teacher and adding in Lecture\" ");
                    System.out.print("Enter lecture");
                    int enterLectureID = enterID();
                    if (lectureRepository.getById(enterLectureID) == null) {
                        break;
                    }

                    System.out.println("Creating Teacher");
                    Person personTeacher = personService.createPerson(nameObject(), Role.TEACHER, courseRepository.getAll()[0]);
                    personRepository.add(personTeacher);
                    System.out.println("================================");
                    System.out.println("Total " + personRepository.objectsTotalTeacher() + " Teacher objects");

                    // set Teacher in Lecture

                    lectureRepository.setTeacher(lectureRepository.getById(enterLectureID), personTeacher);


                    break;

                case 6:
                    System.out.println("Selected   - \"6 - Creating student\" ");
                    personRepository.add(personService.createPerson(nameObject(), Role.STUDENT, courseRepository.getAll()[0]));
                    System.out.println("================================");
                    System.out.println("Total " + personRepository.objectsTotalStudent() + " Student objects");
                    break;

                case 7:
                    System.out.println("Selected   - \"7 - Open Lecture by ID\" ");
                    System.out.print("Enter");
                    int enterID = enterID();
                    if (lectureRepository.getById(enterID) != null) {
                        System.out.println(lectureRepository.getById(enterID).toString());
                    }
                    break;
                case 8:
                    System.out.println("Selected   - \"8 - Delete Lecture by ID\" ");
                    System.out.print("Enter lecture");
                    lectureRepository.deleteById(enterID());
                    break;


            }
            if (category == 0) {
                System.out.println("Exiting program ...");
                break;
            }

        }
    }

    private static int checkCorrect() {
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
            System.out.println("5 - Creating teacher and adding in Lecture");
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

    private static String nameObject() {
        System.out.println("Enter the name of the object:");
        return new Scanner(System.in).next();
    }

    private static int enterID() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" object id:");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter ID");
                scanner.next();
            }
        }
    }


}

