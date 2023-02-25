package repositories;

import models.Person;
import models.model_enum.Role;
import utils.MenuUtils;
import utils.log.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRepository implements Repository<Person> {
    private static PersonRepository instance;


    String nameLog = "Log OnlineSchool";

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

    public Optional<Person> getByIdPerson(Integer id, Role role) {
        return repository.stream().filter(element -> element.getID().equals(id) && element.getRole() == role).findAny();
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

    public boolean checkDoubleEmail(String email) {
        return repository.stream().anyMatch(el -> el.getEmail().equals(email));
    }

    public void printMap() {
        repository.stream()
                .collect(Collectors.toMap(
                        Person::getEmail,
                        v -> v.getName() + " " + v.getLastName()
                ))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public void emailToFile() throws IOException {
        Path filePath = Paths.get(MenuUtils.STR_PATH_DIRECTORY, MenuUtils.STR_NAME_FILe_EMAIL);
        System.out.println("Result in file - " + filePath);
        creatFile(filePath);
        repository.stream()
                .map(Person::getEmail)
                .sorted((String::compareTo))
                .forEach(str -> {
                    try {
                        Files.write(filePath, (str + "\n").getBytes(), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        Log.error(nameLog, "Error in PersonRepository emailToFile", e.getStackTrace());
                    }
                });
    }

    private void creatFile(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            Log.warning(nameLog, "Warning in PersonRepository  createFile method", e.getStackTrace());
        }
    }
}

