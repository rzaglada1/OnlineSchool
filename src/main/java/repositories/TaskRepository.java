package repositories;

import models.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepository implements Repository<Task> {
    private static TaskRepository instance;

    private final List<Task> repository;

    private TaskRepository() {
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
    public Optional<Task> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Task> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Task::getName))
                .collect(Collectors.toList());
    }
}
