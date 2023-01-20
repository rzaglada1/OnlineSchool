package repositories;

import exceptions.EntityNotFoundException;
import models.Task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository implements Repository<Task> {
    private static TaskRepository instance;

    private final List<Task> repository;

    private TaskRepository () {
        repository = new ArrayList<>();
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    @Override
    public List<Task> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Task getById(Integer id) throws EntityNotFoundException {
        for (Task element : repository) {
            if (element.getID().equals(id) ) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");

    }
    @Override
    public List<Task> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(repo -> repo.getName()))
                .collect(Collectors.toList());
    }
}
