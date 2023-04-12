package repositories;


import models.Lecture;
import models.model_enum.Role;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture>, Serializable {

//    {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        CourseRepository courseRepository = context.getBean("courseRepository", CourseRepository.class);
//
//        PersonRepository personRepository = PersonRepository.getInstance();
//
//
//
//        repository = new ArrayList<>();
//
//        try {
//            for (int i = 0; i < 5; i++) {
//                //Thread.sleep(1);
//                getRepository().add(new Lecture(
//                                "Lecture " + i
//                                , courseRepository.getById(1).orElseThrow()
//                                , LocalDateTime.of(2022, 4, 8-i, 19,30 )
//                                , personRepository.getByIdPerson(1,Role.TEACHER).orElseThrow()
//                        ));
//            }
//            for (int i = 0; i < 3; i++) {
//                getRepository().add(new Lecture(
//                                "LectureSecond " + i
//                                , courseRepository.getById(1).orElseThrow()
//                                , LocalDateTime.now()
//                                , personRepository.getByIdPerson(4,Role.TEACHER).orElseThrow()
//                        ));
//            }
//
//        }  catch (NoSuchElementException e) {
//            e.getStackTrace();
//        }
//
//    }

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



}
