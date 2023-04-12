package repositories;

import models.Person;
import models.model_enum.Role;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.PersonService;
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


public class PersonRepository implements Repository<Person> {
    {
        repository = new ArrayList<>();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CourseRepository courseRepository = context.getBean("courseRepository", CourseRepository.class);
        // creating Person
        getRepository().add(new PersonService().create(new String[]{"Наталія", "Романенко"
                , "+380989584545", "Roma1@gmail.com"}, Role.STUDENT, courseRepository.getById(1).orElseThrow()));
        getRepository().get(0).getCourses().add(courseRepository.getById(2).orElseThrow());

        getRepository().add(new PersonService().create(new String[]{"Михайло", "Водерацький"
                , "+380989584545", "Miha@gmail.com"}, Role.TEACHER, courseRepository.getById(1).orElseThrow()));
        getRepository().add(new PersonService().create(new String[]{"Олена", "Ломачевський"
                , "+380989584545", "Olena@gmail.com"}, Role.STUDENT, courseRepository.getById(1).orElseThrow()));
        getRepository().add(new PersonService().create(new String[]{"Зоя", "Андрієнко"
                , "+380989584545", "Andry@meta.org"}, Role.STUDENT, courseRepository.getById(1).orElseThrow()));
        getRepository().add(new PersonService().create(new String[]{"Олена", "Командна"
                , "+380989584545", "Koman@tele.com"}, Role.TEACHER, courseRepository.getById(1).orElseThrow()));
        getRepository().add(new PersonService().create(new String[]{"Галина", "Заворотнюк"
                , "+380989584545", "Depend@ukr.ua"}, Role.STUDENT, courseRepository.getById(2).orElseThrow()));

    }
    private static PersonRepository instance;


    String nameLog = "Log OnlineSchool";

    private final List<Person> repository;

    private PersonRepository() {

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
                .collect(toList());
    }

    public List<Person> sortedStudentByLastName() {
        return repository.stream()
                .filter(p->p.getRole().equals(Role.STUDENT))
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(toList());
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

    public List<Person> NameFilterByChar() {
        return repository.stream()
                .filter(element -> element.getRole().equals(Role.TEACHER)
                        && ( (element.getLastName().compareTo("N") < 0) || element.getLastName().compareTo("Н") < 0) )
                .toList();
    }

    public Map<Person, Integer> sortedByCourses () {
        Map<Person, Integer> personLongMap = repository.stream()
                .filter(el->el.getRole().equals(Role.STUDENT))
                .collect(
                        Collectors.toMap(p->p, e->e.getCourses().size())
                );

        return personLongMap.entrySet().stream()
                .sorted(Comparator.comparing(k->k.getKey().getLastName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k,v) -> k,LinkedHashMap::new));
    }

}

