package repositories;

import exceptions.EntityNotFoundException;
import models.Homework;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWorkRepository implements Repository<Homework> {
    private static HomeWorkRepository instance;

    private final List<Homework> repository;

    private HomeWorkRepository () {
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
    public Homework getById(Integer id) throws EntityNotFoundException {
        for (Homework element : repository) {
            if (element.getID().equals(id) ) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");

    }

    @Override
    public List<Homework> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(repo -> repo.getName()))
                .collect(Collectors.toList());
    }

    public List<Homework> getHomeworkByLectureId (int lectureId) throws EntityNotFoundException {
        return repository.stream()
                .filter(element -> element.getLectureID() == lectureId).collect(Collectors.toList());
    }

}
