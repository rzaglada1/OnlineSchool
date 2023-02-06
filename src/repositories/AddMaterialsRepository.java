package repositories;

import models.AddMaterials;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddMaterialsRepository implements Repository<AddMaterials> {

    private static AddMaterialsRepository instance;
    private final List<AddMaterials> repository;

    private AddMaterialsRepository() {
        repository = new ArrayList<>();
    }

    public static AddMaterialsRepository getInstance() {
        if (instance == null) {
            instance = new AddMaterialsRepository();
        }
        return instance;
    }

    @Override
    public List<AddMaterials> getRepository() {
        return repository;
    }

    @Override
    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Optional<AddMaterials> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<AddMaterials> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(AddMaterials::getName))
                .collect(Collectors.toList());
    }

    public List<AddMaterials> getAddMaterialsByLectureId(int lectureId) {
        return repository.stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get() == lectureId).collect(Collectors.toList());
    }

    public void printAddMaterialsByLectureId(int lectureID) {
        Predicate<AddMaterials> filterByLectureID = element -> element.getLectureID().isPresent()
                && element.getLectureID().get() == lectureID;
        repository.stream().filter(filterByLectureID).forEach(System.out::println);
    }


}
