package repositories;

import models.Person;
import models.model_enum.Role;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import services.CourseService;

import java.util.*;

import static java.util.stream.Collectors.toList;


public class PersonRepository implements Repository<Person>, InitializingBean {

    CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


    private final List<Person> repository = new ArrayList<>();


    @Override
    public List<Person> getRepository() {
        return repository;
    }

    @Override
    public Optional<Person> getById(long id) {
        return repository.stream().filter(element -> element.getID() == id).findAny();
    }

    @Override
    public List<Person> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(toList());
    }


    @Override
    public void afterPropertiesSet() {

        //create persons
        getRepository().add(new Person(new String[]{"Наталія", "Романенко"
                , "+380989584545", "Roma1@gmail.com"}, Role.STUDENT, courseService.getCourseById(1).orElseThrow()));
        getRepository().get(0).getCourses().add(courseService.getCourseById(2).orElseThrow());

        getRepository().add(new Person(new String[]{"Михайло", "Водерацький"
                , "+380989584545", "Miha@gmail.com"}, Role.TEACHER, courseService.getCourseById(1).orElseThrow()));
        getRepository().add(new Person(new String[]{"Олена", "Ломачевський"
                , "+380989584545", "Olena@gmail.com"}, Role.STUDENT, courseService.getCourseById(1).orElseThrow()));
        getRepository().add(new Person(new String[]{"Зоя", "Андрієнко"
                , "+380989584545", "Andry@meta.org"}, Role.STUDENT, courseService.getCourseById(1).orElseThrow()));
        getRepository().add(new Person(new String[]{"Олена", "Командна"
                , "+380989584545", "Koman@tele.com"}, Role.TEACHER, courseService.getCourseById(1).orElseThrow()));
        getRepository().add(new Person(new String[]{"Галина", "Заворотнюк"
                , "+380989584545", "Depend@ukr.ua"}, Role.STUDENT, courseService.getCourseById(2).orElseThrow()));


    }
}

