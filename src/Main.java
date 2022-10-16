import models.Course;
import models.Lecture;
import services.CourseServices;
import services.LectureServices;

public class Main {
    public static void main(String[] args) {

        LectureServices lectureServicesFirst = new LectureServices();
        lectureServicesFirst.createLecture();
        LectureServices lectureServicesSecond = new LectureServices();
        lectureServicesSecond.createLecture();
        LectureServices lectureServicesThird = new LectureServices();
        lectureServicesThird.createLecture();

        System.out.println(Lecture.CREATE_COUNT_LECTURE + " objects created");

    }
}