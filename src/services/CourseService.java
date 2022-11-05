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

    public Course createCurse(String nameCourse) {
        return new Course(nameCourse);
    }

    public Course createCurse(String nameCourse, Teacher teacher, Student student, Lecture lecture) {
        return new Course(nameCourse, teacher, student, lecture);
    }

    public void printObjectsRepository(CourseRepository courseRepository) {

        Course[] courseArray = courseRepository.getCourseRepository();

        for (Course course : courseArray) {
            if (course == null) {
                break;
            }
            System.out.println("Name course - \"" + course.getNameCourse() + "\". Course id = " + course.getIdCourse());

        }
    }
}
