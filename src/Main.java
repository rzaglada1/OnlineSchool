import models.Lecture;
import services.LectureService;

public class Main {
    public static void main(String[] args) {

        LectureService lectureServiceFirst = new LectureService();
        lectureServiceFirst.createLecture();
        LectureService lectureServiceSecond = new LectureService();
        lectureServiceSecond.createLecture();
        LectureService lectureServiceThird = new LectureService();
        lectureServiceThird.createLecture();

        System.out.println(Lecture.CREATE_COUNT_LECTURE + " objects created");

    }
}