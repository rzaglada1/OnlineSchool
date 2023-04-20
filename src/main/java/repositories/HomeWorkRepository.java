package repositories;


import models.Homework;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class HomeWorkRepository implements Repository<Homework>, InitializingBean {


    private LectureRepository lectureRepository;

    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    private final List<Homework> repository = new ArrayList<>();


    @Override
    public List<Homework> getRepository() {
        return repository;
    }


    @Override
    public Optional<Homework> getById(long id) {
        return repository.stream().filter(element -> element.getID() == id).findAny();
    }

    @Override
    public List<Homework> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Homework::getName))
                .collect(Collectors.toList());
    }


    @Override
    public void afterPropertiesSet() {
        //create homeworks
        try {

            getRepository().add(new Homework(
                    "homeworkLecture"
                    , lectureRepository.getById(1).orElseThrow()
            ));
            getRepository().add(new Homework(
                    "homeworkLecture"
                    , lectureRepository.getById(2).orElseThrow()
            ));

            getRepository().add(new Homework(
                    "homeworkLecture"
                    , lectureRepository.getById(2).orElseThrow()
            ));
            getRepository().add(new Homework(
                    "homeworkLecture"
                    , lectureRepository.getById(3).orElseThrow()
            ));
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
}
