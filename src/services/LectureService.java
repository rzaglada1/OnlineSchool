package services;


import models.Course;
import models.Lecture;
import java.time.LocalDateTime;


public class LectureService {

    public Lecture create() {
        return new Lecture();
    }

    public Lecture create(String name) {
        return new Lecture(name);
    }


    public Lecture create(String name, Course course) {
        return new Lecture(name, course);
    }

    public Lecture create(String name, Course course, LocalDateTime lectureDate) {
        return new Lecture(name, course, lectureDate);
    }


}
