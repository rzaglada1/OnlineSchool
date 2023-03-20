package services;

import models.*;


public class CourseService {

    public Course create() {
        return new Course();
    }

    public Course create(String name) {
        return new Course(name);
    }



}
