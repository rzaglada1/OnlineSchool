package services;

import models.model_enum.ResourceType;
import models.AddMaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AddMaterialsRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AddMaterialsService  {


    private AddMaterialsRepository addMaterialsRepository;

    public AddMaterialsService() {
    }

    @Autowired
    public void setAddMaterialsRepository(AddMaterialsRepository addMaterialsRepository) {
        this.addMaterialsRepository = addMaterialsRepository;
    }

    public List<AddMaterials> getAllAddMaterials() {
        return addMaterialsRepository.findAll();
    }

    public void printRepository() {
        getAllAddMaterials().forEach(System.out::println);
    }

    public Optional<AddMaterials> getAddMaterialsById(Long id) {
        return addMaterialsRepository.findById(id);
    }


    public List<AddMaterials> sortedAddMaterialsByName() {
        Sort sort = Sort.by("name").ascending();
        return addMaterialsRepository.findAll(sort);
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

    @Transactional
    public void savePerson(AddMaterials addMaterials) {
        addMaterialsRepository.save(addMaterials);
    }


}
