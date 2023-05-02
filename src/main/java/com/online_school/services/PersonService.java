package com.online_school.services;


import com.online_school.Main;
import com.online_school.models.Person;
import com.online_school.models.model_enum.Role;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.online_school.repositories.PersonRepository;
import com.online_school.utils.log.Log;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Service
public class PersonService {
    String nameLog = "Log OnlineSchool";
    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson() {
        return personRepository.findAll();

    }

    public void printRepository() {
        getAllPerson().forEach(System.out::println);
    }

    public Optional<Person> getById(long id) {
        return personRepository.findById(id);
    }

    public List<Person> sortedByName() {
        Sort sort = Sort.by("name").ascending();
        return personRepository.findAll(sort);
    }

    public void printRepositoryStudent() {
        getAllPerson().stream().filter(element -> element.getRole() == Role.STUDENT).forEach(System.out::println);
    }

    public void printRepositoryTeacher() {
        getAllPerson().stream().filter(element -> element.getRole() == Role.TEACHER).forEach(System.out::println);
    }

    public Optional<Person> getByIdPerson(Long id) {
        return personRepository.findById(id);
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
        Path filePath = Paths.get(Main.STR_PATH_DIRECTORY, Main.STR_NAME_FILe_EMAIL);
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
        Map<Person, Integer> personIntegerMap = new HashMap<>();
        Person person;
        for (Object[] objects : personRepository.findByCountCourse()) {
            person = new Person();
            String name = (String) objects[0];
            String lastName = (String) objects[1];
            int count = Integer.parseInt(objects[2].toString());
            person.setName(name);
            person.setLastName(lastName);
            personIntegerMap.put(person, count);
        }
        return personIntegerMap.entrySet().stream()
                .sorted(Comparator.comparing(k -> k.getKey().getLastName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
    }

    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }

}
