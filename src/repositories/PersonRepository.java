package repositories;

import exceptions.EntityNotFoundException;
import models.Person;
import models.model_enum.Role;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRepository implements Repository<Person> {
    private static PersonRepository instance;

    private final List<Person> repository;

    private PersonRepository() {
        repository = new ArrayList<>();
    }

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        return instance;
    }

    public void printRepositoryStudent() {
        repository.forEach(person -> {
                    if (person.getRole() == Role.STUDENT) {
                        System.out.println(person);
                    }
                }
        );
    }

    public void printRepositoryTeacher() {
        repository.forEach(person -> {
                    if (person.getRole() == Role.TEACHER) {
                        System.out.println(person);
                    }
                }
        );
    }

    @Override
    public List<Person> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Person getById(Integer id) throws EntityNotFoundException {
        for (Person element : repository) {
            if (element.getID().equals(id)) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");

    }

    @Override
    public List<Person> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(repo -> repo.getLastName()))
                .collect(Collectors.toList());
    }



}
