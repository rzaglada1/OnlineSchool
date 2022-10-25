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


        //  Homework N7  part 1.
        System.out.println("");
        System.out.println("========= Homework N7  part 1 ================");
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

        System.out.println("Select category:");
        System.out.println("1 - Course");
        System.out.println("2 - Lecture");
        System.out.println("3 - Teacher");
        System.out.println("4 - Student");

        int category = scanner.nextInt();

        switch (category) {
            case 1:
                System.out.println("Selected category  - \"Course\" ");
                System.out.println(course.toString());
                break;

            case 2:
                System.out.println("Selected category  - \"Lecture\" ");
                System.out.println(lectureCourse1.toString());
                break;

            case 3:
                System.out.println("Selected category  - \"Teacher\" ");
                System.out.println(teacherCourse1.toString());
                break;

            case 4:
                System.out.println("Selected category  - \"Student\" ");
                System.out.println(studentCourse1.toString());
                break;

            default:
                System.out.println("Selection error");
                break;
        }

        if (Course.CREATE_COUNT_COURSE == 0) {
            // Lecture lectureCourse1 = lectureService.createLecture("Java 1");
        }


        //  Homework N7  part 2  and part3.

        System.out.println("");
        System.out.println("========= Homework N7  part 2 & part 3 ================");
        System.out.println("");

        String nameLecture = "";
        while (true) {
            System.out.println("");
            System.out.println("Creating object lecture.  Enter name lecture:");
            System.out.println("\"1\" - for exit program");

            nameLecture = scanner.next();

            if (nameLecture.equals("1")) {
                System.out.println("Exiting the program...");
                break;
            } else {
                lectureService.createLecture(nameLecture);
                System.out.println("ID Course = " + course.getIdCourse());
                System.out.println(Lecture.CREATE_COUNT_LECTURE +  " objects lectures created");
            }
        }



//        if (category == 2) {
//            System.out.println("Creating object lecture. Enter name lecture");
//            String nameLecture = scanner.next();
//            Lecture lectureCourse1 = lectureService.createLecture(nameLecture);
//        } else {
//            Lecture lectureCourse1 = lectureService.createLecture("Java 1");
//        }

    }

}