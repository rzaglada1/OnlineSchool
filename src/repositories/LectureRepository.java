package repositories;


import exceptions.EntityNotFoundException;
import models.Lecture;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture>, Serializable {
    private static LectureRepository instance;

    private final List<Lecture> repository;

    private LectureRepository() {
        repository = new ArrayList<>();
    }

    public static LectureRepository getInstance() {
        if (instance == null) {
            instance = new LectureRepository();
        }
        return instance;
    }

    @Override
    public List<Lecture> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Lecture getById(Integer id) throws EntityNotFoundException {
        for (Lecture element : repository) {
            if (element.getID().equals(id)) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");
    }

    @Override
    public List<Lecture> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Lecture::getName))
                .collect(Collectors.toList());
    }

    public void printAfterBeforeDate (LocalDateTime startDateTime, LocalDateTime finishDateTime) {
        Predicate<Lecture> plAfter = element -> element.getCreationDate().isAfter(startDateTime);
        System.out.println('\n' + "Lecture after " + startDateTime);
        System.out.println("========================================");
        repository.stream().filter(plAfter).forEach(System.out::println);

        Predicate<Lecture> plBefore = element -> element.getCreationDate().isBefore(startDateTime);
        System.out.println('\n' + "Lecture before " + startDateTime);
        System.out.println("========================================");
        repository.stream().filter(plBefore).forEach(System.out::println);

                Predicate<Lecture> pb = element -> element.getCreationDate().isAfter(startDateTime)
                && element.getCreationDate().isBefore(finishDateTime);
        System.out.println('\n' + "Lecture between startDate : " + startDateTime +  " - finishDate:  " + finishDateTime);
        System.out.println("========================================");
        repository.stream().filter(pb).forEach(System.out::println);

    }

}
