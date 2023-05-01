package services;

import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService  {

    private CourseRepository courseRepository;

    public CourseService() {
    }


    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }


    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }


    public List<Course> sortedCourseByName() {
        Sort sort = Sort.by("name").ascending();
        return courseRepository.findAll(sort);
    }

    public void printRepository() {
        getAllCourse().forEach(System.out::println);
    }


    @Transactional
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }


}
