package repositories;

import models.Person;
import models.model_enum.Role;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        repository.stream().filter(element -> element.getRole() == Role.STUDENT).forEach(System.out::println);
    }

    public void printRepositoryTeacher() {
        repository.stream().filter(element -> element.getRole() == Role.TEACHER).forEach(System.out::println);
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
    public Optional<Person> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Person> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
    }

    public void printNameFilter(char charFilter) {
        System.out.println("Printing last names teachers which begin char < '" + charFilter + "'");
        repository.stream()
                .filter(element -> element.getRole().equals(Role.TEACHER)
                        && element.getLastName().compareTo(String.valueOf(charFilter)) < 0)
                .forEach(System.out::println);

    }


}
