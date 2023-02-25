package repositories;

import models.Course;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    public Optional<Course> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Course> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

}
