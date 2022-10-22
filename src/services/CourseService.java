package services;

import models.Course;

public class CourseService {

    public Course createCurse() {
        return new Course();
    }

    public Course createCurse(Integer idCourse, String nameCourse) {
        return new Course(idCourse, nameCourse);
    }
}
