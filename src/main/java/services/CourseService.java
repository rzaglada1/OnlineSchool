package services;

import models.*;


public class CourseService {

    public Course create() {
        return new Course();
    }

    public Course create(String name) {
        return new Course(name);
    }

    public Course create(String name, Person person, Lecture lecture) {
        return new Course(name, person, lecture);
    }

}
