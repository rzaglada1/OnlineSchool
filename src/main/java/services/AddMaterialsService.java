package services;


import models.model_enum.ResourceType;
import models.AddMaterials;
import repositories.AddMaterialsRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddMaterialsService implements Service {

    private final AddMaterialsRepository addMaterialsRepository;


    public AddMaterialsService(AddMaterialsRepository addMaterialsRepository) {
        this.addMaterialsRepository = addMaterialsRepository;
    }


    public List<AddMaterials> getAllAddMaterials() {
        return addMaterialsRepository.getRepository();
    }

    public void printRepository() {
        getAllAddMaterials().forEach(System.out::println);
    }

    public Optional<AddMaterials> getAddMaterialsById(Integer id) {
        return addMaterialsRepository.getById(id);
    }


    public List<AddMaterials> sorteAddMaterialsdByName() {
        return addMaterialsRepository.sortedByName();
    }

    public List<AddMaterials> getAddMaterialsByLectureId(int lectureId) {
        return getAllAddMaterials().stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get() == lectureId).collect(Collectors.toList());
    }

    public void printAddMaterialsByLectureId(int lectureID) {
        Predicate<AddMaterials> filterByLectureID = element -> element.getLectureID().isPresent()
                && element.getLectureID().get() == lectureID;
        getAllAddMaterials().stream().filter(filterByLectureID).forEach(System.out::println);
    }


    public void printAddMaterialsGroupByLecture() {
        getAllAddMaterials().stream()
                .collect(Collectors.groupingBy(AddMaterials::getLecture))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public Map<ResourceType, Long> countCategory() {

      //  getAllAddMaterials().forEach(System.out::println);

        return getAllAddMaterials().stream().collect(
                Collectors.groupingBy(AddMaterials::getResourceType, Collectors.counting()));

    }


}
