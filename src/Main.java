import models.Course;
import models.Lecture;
import services.CourseService;
import services.LectureService;

public class Main {
    public static void main(String[] args) {


        CourseService courseService1 = new CourseService();
        Course course = courseService1.createCurse("Java course");

        LectureService lectureService = new LectureService();
        Lecture lecture1 = lectureService.createLecture("Java 1", course);
        Lecture lecture2 = lectureService.createLecture("Java 2", course);
        Lecture lecture3 = lectureService.createLecture("Java 3", course);
        Lecture lecture4 = lectureService.createLecture("Java 4", course);
        Lecture lecture5 = lectureService.createLecture("Java 5", course);
        Lecture lecture6 = lectureService.createLecture("Java 6", course);

        System.out.println("Name course - \"" + course.getNameCourse() + "\"");
        System.out.println("Name Lecture 6 - \"" + lecture6.getNameLecture() +
                "\". ID course = " + lecture6.getIdCourse());
        System.out.println("Created " + Lecture.CREATE_COUNT_LECTURE + " lecture objects");
    }
}