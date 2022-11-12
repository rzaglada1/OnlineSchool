package services;

import models.Course;
import models.Lecture;
import models.Student;
import models.Teacher;
import repositories.CourseRepository;


public class CourseService {

    public Course createCurse() {
        return new Course();
    }

    public Course createCurse(String name) {
        return new Course(name);
    }

    public Course createCurse(String name, Teacher teacher, Student student, Lecture lecture) {
        return new Course(name, teacher, student, lecture);
    }

    public void printObjectsRepository(CourseRepository courseRepository) {

        Course[] courseArray = courseRepository.getRepository();

        for (Course course : courseArray) {
            if (course == null) {
                break;
            }
            System.out.println("Name course - \"" + course.getName () + "\". Course id = " + course.getId ());

        }
    }
}
