package repositories;

import exceptions.EntityNotFoundException;
import models.Course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseRepository implements Repository<Course> {

    private static CourseRepository instance;

    private final List<Course> repository;

    private CourseRepository() {
        repository = new ArrayList<>();
    }

    public static CourseRepository getInstance() {
        if (instance == null) {
            instance = new CourseRepository();
        }
        return instance;
    }

    @Override
    public List<Course> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Course getById(Integer id) throws EntityNotFoundException {
        for (Course element : repository) {
            if (element.getID().equals(id)) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");

    }

    @Override
    public List<Course> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

}
