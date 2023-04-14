package repositories;


import models.Lecture;
import models.model_enum.Role;
import org.springframework.beans.factory.InitializingBean;
import services.CourseService;
import services.PersonService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture>, Serializable, InitializingBean {

    private CourseService courseService;
    private PersonService personService;

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    private final List<Lecture> repository = new ArrayList<>();


    @Override
    public List<Lecture> getRepository() {
        return repository;
    }


    @Override
    public Optional<Lecture> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Lecture> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Lecture::getName))
                .collect(Collectors.toList());
    }


    @Override
    public void afterPropertiesSet() {
        // create lectures
        try {
            for (int i = 0; i < 5; i++) {
                getRepository().add(new Lecture(
                        "Lecture " + i
                        , courseService.getCourseById(1).orElseThrow()
                        , LocalDateTime.of(2022, 4, 8 - i, 19, 30)
                        , personService.getByIdPerson(1, Role.TEACHER).orElseThrow()
                ));
            }

            for (int i = 0; i < 3; i++) {
                getRepository().add(new Lecture(
                        "LectureSecond " + i
                        , courseService.getCourseById(1).orElseThrow()
                        , LocalDateTime.now()
                        , personService.getByIdPerson(4, Role.TEACHER).orElseThrow()
                ));
            }

        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
}
