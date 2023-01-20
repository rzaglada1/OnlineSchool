package repositories;


import exceptions.EntityNotFoundException;
import models.Lecture;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture> {
    private static LectureRepository instance;

    private final List<Lecture> repository;

    private LectureRepository () {
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
            if (element.getID().equals(id) ) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");
    }

    @Override
    public List<Lecture> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(repo -> repo.getName()))
                .collect(Collectors.toList());
    }


}
