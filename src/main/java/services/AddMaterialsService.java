package services;

import models.model_enum.ResourceType;
import models.AddMaterials;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.AddMaterialsRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddMaterialsService implements Service {


    private AddMaterialsRepository addMaterialsRepository;

    public AddMaterialsService() {
    }

    @Autowired
    public void setAddMaterialsRepository(AddMaterialsRepository addMaterialsRepository) {
        this.addMaterialsRepository = addMaterialsRepository;
    }

    public List<AddMaterials> getAllAddMaterials() {
        return addMaterialsRepository.getRepository();
    }

    public void printRepository() {
        getAllAddMaterials().forEach(System.out::println);
    }

    public Optional<AddMaterials> getAddMaterialsById(Long id) {
        return addMaterialsRepository.getById(id);
    }


    public List<AddMaterials> sortedAddMaterialsByName() {
        return addMaterialsRepository.sortedByName();
    }

    public List<AddMaterials> getAddMaterialsByLectureId(long lectureId) {
        return getAllAddMaterials().stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get() == lectureId).collect(Collectors.toList());
    }

    public void printAddMaterialsByLectureId(long lectureID) {
        Predicate<AddMaterials> filterByLectureID = element -> element.getLectureID().isPresent()
                && element.getLecture().getID() == lectureID;
        getAllAddMaterials().stream().filter(filterByLectureID).forEach(System.out::println);
    }


    public void printAddMaterialsGroupByLecture() {
        getAllAddMaterials().stream()
                .collect(Collectors.groupingBy(AddMaterials::getLecture))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public Map<ResourceType, Long> countCategory() {
        return getAllAddMaterials().stream().collect(
                Collectors.groupingBy(AddMaterials::getResourceType, Collectors.counting()));

    }

    public void savePerson(AddMaterials addMaterials) {
        addMaterialsRepository.saveAddMaterialsToRepository(addMaterials);
    }


}
