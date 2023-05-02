package com.online_school.services;

import com.online_school.models.Course;
import com.online_school.repositories.CourseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
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


    public void saveCourse(Course course) {
        courseRepository.save(course);
    }


}
