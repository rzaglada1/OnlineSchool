package services;

import models.*;
import repositories.CourseRepository;
import java.util.List;
import java.util.Optional;


public class CourseService implements Service {
    private final String nameLog = "Log OnlineSchool";

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourse() {
        return courseRepository.getRepository();
    }

    public Optional<Course> getCourseById(Integer id) {
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
