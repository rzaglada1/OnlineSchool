package services;

import models.Course;

public class CourseService {

    public Course createCurse() {
        return new Course();
    }

    public Course createCurse(String nameCourse) {
        return new Course(nameCourse);
    }
}
