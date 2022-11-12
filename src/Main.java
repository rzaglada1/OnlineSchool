

import models.Course;
import models.Lecture;
import models.Student;
import models.Teacher;
import repositories.*;
import services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //  Homework N9 .
        System.out.println("");
        System.out.println("========= Homework N9  ================");
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
        int idCourse = courseRepository.getRepository()[0].getId();
        System.out.println("idCourse  " + idCourse);

        for (int i = 0; i < 300; i++) {
            lectureRepository.add(lectureService.createLecture("Lecture " + String.valueOf(i), idCourse));
        }

        System.out.println("Created " + Lecture.getCreateCount() + " lecture objects ");

        // printing repository objects
        lectureService.printObjectsRepository(lectureRepository);


        while (true) {

            int category = checkCorrect();

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"1 - Objects course\" ");
                    System.out.println("");
                    // printing repository objects
                    courseService.printObjectsRepository(courseRepository);
                    break;

                case 2:
                    System.out.println("Selected   - \"2 - Objects lecture\" ");
                    System.out.println("");
                    // printing repository objects
                    lectureService.printObjectsRepository(lectureRepository);
                    break;

                case 3:
                    System.out.println("Selected   - \"3 - Creating course\" ");
                    courseRepository.add( courseService.createCurse(nameObject()) );
                    System.out.println("Total of " + Course.getCreateCount () + " Course objects created");
                    break;

                case 4:
                    System.out.println("Selected   - \"4 - Creating lecture\" ");
                    lectureRepository.add( lectureService.createLecture(nameObject()) );
                    System.out.println("Total of " + Lecture.getCreateCount () + " Lecture objects created");
                    break;

                case 5:
                    System.out.println("Selected   - \"5 - Creating teacher\" ");
                    teacherRepository.add( teacherService.createTeacher(nameObject()) );
                    System.out.println("Total of " + Teacher.getCreateCount () + " Teacher objects created");

                    break;

                case 6:
                    System.out.println("Selected   - \"6 - Creating student\" ");
                    studentRepository.add( studentService.createStudent(nameObject()) );
                    System.out.println("Total of " + Student.getCreateCount () + " Student objects created");
                    break;


            }
            if (category == 7) {
                System.out.println("Exiting program ...");
                break;
            }

        }
    }

    private static int checkCorrect() {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstRun = false;
        int category = -1;
        while (category > 7 || category < 1 ) {
            if (isFirstRun) {
                System.out.println("Something went wrong ... Try again. ");
            }
            isFirstRun = true;

            System.out.println("");
            System.out.println("Select category:");
            System.out.println("1 - Objects course");
            System.out.println("2 - Objects lecture");
            System.out.println("3 - Creating course");
            System.out.println("4 - Creating lecture");
            System.out.println("5 - Creating teacher");
            System.out.println("6 - Creating student");
            System.out.println("7 - exit program");

            if (scanner.hasNextInt()) {
                category = scanner.nextInt();
            } else {
                scanner.next();
            }

      }

        return category;
    }

    private static String nameObject () {
        System.out.println("Enter the name of the object:");
        return   new Scanner(System.in).next();
    }


}

