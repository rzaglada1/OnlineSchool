package repositories;


import models.Homework;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomeWorkRepository implements Repository<Homework> {
    private static HomeWorkRepository instance;
    private final List<Homework> repository;

    private HomeWorkRepository() {
        repository = new ArrayList<>();
    }

    public static HomeWorkRepository getInstance() {
        if (instance == null) {
            instance = new HomeWorkRepository();
        }
        return instance;
    }

    @Override
    public List<Homework> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Optional<Homework> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Homework> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Homework::getName))
                .collect(Collectors.toList());
    }

    public List<Homework> getHomeworkByLectureId(int lectureId) {
        return repository.stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get()  == lectureId).collect(Collectors.toList());
    }

}
