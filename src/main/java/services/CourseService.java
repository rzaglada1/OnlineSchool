package services;

import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.CourseRepository;

import java.util.List;
import java.util.Optional;


public class CourseService implements Service {

    private CourseRepository courseRepository;

    public CourseService() {
    }


    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourse() {
        return courseRepository.getRepository();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.getById(id);
    }


    public List<Course> sortedCourseByName() {
        return courseRepository.sortedByName();
    }

    public void printRepository() {
        getAllCourse().forEach(System.out::println);
    }


    public void saveCourse(Course course) {
        courseRepository.saveCourseToRepository(course);
    }


}
