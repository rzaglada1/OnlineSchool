package services;

import models.Course;
import models.Lecture;

public class LectureService {

    public Lecture createLecture() {
        return new Lecture();
    }

    public Lecture createLecture(String nameLecture, Course course) {
        return new Lecture(nameLecture, course);
    }
}
