import models.Course;
import models.Lecture;
import models.Student;
import models.Teacher;
import services.CourseService;
import services.LectureService;
import services.StudentService;
import services.TeacherService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //  Homework N8 .
        System.out.println("");
        System.out.println("========= Homework N8  ================");
        System.out.println("");

        TeacherService teacherService = new TeacherService();
        Teacher teacherCourse1 = teacherService.createTeacher("Anna");

        StudentService studentService = new StudentService();
        Student studentCourse1 = studentService.createStudent("Roman");

        LectureService lectureService = new LectureService();
        Lecture lectureCourse1 = lectureService.createLecture("Java 1");

        // creating a course with teacher, student, lecture
        CourseService courseService = new CourseService();
        Course course = courseService.createCurse("Java course", teacherCourse1, studentCourse1, lectureCourse1);

        int category = -1;

        while (true) {
            while (category > 5  || category < 1) {
                System.out.println("");
                System.out.println("Select category:");
                System.out.println("1 - Creating course");
                System.out.println("2 - Creating lecture");
                System.out.println("3 - Creating teacher");
                System.out.println("4 - Creating student");
                System.out.println("5 - exit program");

                category = scanner.nextInt();
            }

            switch (category) {
                case 1:
                    System.out.println("Selected   - \"Creating course\" ");
                    courseService.createCurse();
                    System.out.println(Course.CREATE_COUNT_COURSE + " objects Course created");
                    break;

                case 2:
                    System.out.println("Selected  - \"Creating lecture\" ");
                    lectureService.createLecture();
                    System.out.println(Lecture.CREATE_COUNT_LECTURE + " objects Lecture created");
                    break;

                case 3:
                    System.out.println("Selected  - \"Creating teacher\" ");
                    teacherService.createTeacher();
                    System.out.println(Teacher.CREATE_COUNT_TEACHER + " objects Teacher created");
                    break;

                case 4:
                    System.out.println("Selected  - \"Creating student\" ");
                    studentService.createStudent();
                    System.out.println(Student.CREATE_COUNT_STUDENT + " objects Student created");
                    break;

//                case 5:
//                    System.out.println("Exiting program ...");
//                    break;

            }
            if (Lecture.CREATE_COUNT_LECTURE == 8 || category == 5) {
                System.out.println("Exiting program ...");
                break;
            }
            category = -1;
        }
    }
}