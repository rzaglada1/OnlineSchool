package services;

import models.*;
import repositories.CourseRepository;


public class CourseService {

    public Course createCurse() {
        return new Course();
    }

    public Course createCurse(String name) {
        return new Course(name);
    }

    public Course createCurse(String name, Person person, Lecture lecture) {
        return new Course(name, person, lecture);
    }

    public void printObjectsRepository(CourseRepository courseRepository) {

        Model[] courseArray = courseRepository.getAll();

        for (Model course : courseArray) {
            if (course != null) {
                System.out.println("Name course - \"" + course.getName() + "\". Course id = " + course.getID());
            }


        }
    }
}
