package repositories;

import exceptions.EntityNotFoundException;
import models.AddMaterials;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddMaterialsRepository implements Repository<AddMaterials>{

    private static AddMaterialsRepository instance;

    private final List<AddMaterials> repository;

    private AddMaterialsRepository () {
        repository = new ArrayList<> ();
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
    public AddMaterials getById(Integer id) throws EntityNotFoundException {
        for (AddMaterials element : repository) {
            if (element.getID().equals(id) ) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");

    }

    @Override
    public List<AddMaterials> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(repo -> repo.getName()))
                .collect(Collectors.toList());
    }

    public List<AddMaterials> getAddMaterialsByLectureId (int lectureId) throws EntityNotFoundException {
        return repository.stream()
                .filter(element -> element.getLectureID() == lectureId).collect(Collectors.toList());
    }

}
