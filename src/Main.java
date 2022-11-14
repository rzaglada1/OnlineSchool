

import models.*;
import repositories.*;
import services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //  Homework N9 .
        System.out.println("");
        System.out.println("========= Homework N10  ================");
        System.out.println("");

        CourseService courseService = new CourseService();
        CourseRepository courseRepository = new CourseRepository();

        LectureService lectureService = new LectureService();
        LectureRepository lectureRepository = new LectureRepository();

        TeacherService teacherService = new TeacherService();
        TeacherRepository teacherRepository = new TeacherRepository();

        StudentService studentService = new StudentService();
        StudentRepository studentRepository = new StudentRepository();

        HomeworkService homeworkService = new HomeworkService();
        HomeworkRepository homeworkRepository = new HomeworkRepository();

        AddTaskService addTaskService = new AddTaskService();
        AddTaskRepository addTaskRepository = new AddTaskRepository();


        // creating Course
        courseRepository.add(courseService.createCurse("Java course"));

        // creating three Lectures
        int idCourse = courseRepository.getAll()[0].getID();
        System.out.println("idCourse  " + idCourse);

        for (int i = 0; i < 30; i++) {
            lectureRepository.add(lectureService.createLecture("Lecture " + (i + 1), idCourse));
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
                    lectureService.printObjectsRepository(lectureRepository);
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
                    System.out.println("Selected   - \"5 - Creating teacher\" ");
                    teacherRepository.add(teacherService.createTeacher(nameObject()));
                    System.out.println("================================");
                    System.out.println(Teacher.getCreateCount() + " Teacher objects created since beginning");
                    System.out.println("Total " + teacherRepository.objectsTotal() + " Teacher objects");

                    break;

                case 6:
                    System.out.println("Selected   - \"6 - Creating student\" ");
                    studentRepository.add(studentService.createStudent(nameObject()));
                    System.out.println("================================");
                    System.out.println(Student.getCreateCount() + " Student objects created since beginning");
                    System.out.println("Total " + studentRepository.objectsTotal() + " Student objects");
                    break;

                case 7:
                    System.out.println("Selected   - \"7 - Open Lecture by ID\" ");
                    int enterID = enterID();
                    if (lectureRepository.getById(enterID) != null) {
                        System.out.println(lectureRepository.getById(enterID).toString());
                    } else {
                        System.out.println("111Object with ID = " + enterID + " - not found");
                    }
                    break;
                case 8:
                    System.out.println("Selected   - \"8 - Delete Lecture by ID\" ");
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
            System.out.println("5 - Creating teacher");
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
            System.out.println("Enter object id:");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Please enter ID");
                scanner.next();
            }
        }
    }


}

