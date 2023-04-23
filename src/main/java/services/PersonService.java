package services;


import models.Course;
import models.Person;
import models.model_enum.Role;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.PersonRepository;
import utils.MenuUtils;
import utils.log.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PersonService {
    String nameLog = "Log OnlineSchool";
    private  PersonRepository personRepository;

    public PersonService(){}


    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        return personRepository.getRepository();

    }

    public void printRepository() {
        getAllPerson().forEach(System.out::println);
    }

    public Optional<Person> getById(Integer id) {
        return personRepository.getById(id);
    }

    public List<Person> sortedByName() {
        return personRepository.sortedByName();
    }

    public void printRepositoryStudent() {
        getAllPerson().stream().filter(element -> element.getRole() == Role.STUDENT).forEach(System.out::println);
    }

    public void printRepositoryTeacher() {
        getAllPerson().stream().filter(element -> element.getRole() == Role.TEACHER).forEach(System.out::println);
    }

    public Optional<Person> getByIdPerson(Integer id, Role role) {
        return getAllPerson().stream().filter(element -> element.getID() == id && element.getRole() == role).findAny();
    }

    public List<Person> sortedStudentByLastName() {
        return getAllPerson().stream()
                .filter(p -> p.getRole().equals(Role.STUDENT))
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(toList());
    }


    public void printNameFilter(char charFilter) {
        System.out.println("Printing last names teachers which begin char < '" + charFilter + "'");
        getAllPerson().stream()
                .filter(element -> element.getRole().equals(Role.TEACHER)
                        && element.getLastName().compareTo(String.valueOf(charFilter)) < 0)
                .forEach(System.out::println);

    }

    public boolean checkDoubleEmail(String email) {
        return getAllPerson().stream().anyMatch(el -> el.getEmail().equals(email));
    }

    public void printMap() {
        getAllPerson().stream()
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
        getAllPerson().stream()
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

    public List<Person> NameFilterByChar() {
        return getAllPerson().stream()
                .filter(element -> element.getRole().equals(Role.TEACHER)
                        && ((element.getLastName().compareTo("N") < 0) || element.getLastName().compareTo("Н") < 0))
                .toList();
    }

    public Map<Person, Integer> sortedByCourses() {
        Map<Person, Integer> personLongMap = getAllPerson().stream()
                .filter(el -> el.getRole().equals(Role.STUDENT))
                .collect(
                        Collectors.toMap(p -> p, e -> e.getCourses().size())
                );


        personLongMap.forEach((k,v) -> System.out.println(k +" " + v));

        return personLongMap.entrySet().stream()
                .sorted(Comparator.comparing(k -> k.getKey().getLastName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
    }


    public void savePerson(Person person) {
        personRepository.savePersonToRepository(person);
    }

}
