package com.online_school.services;

import com.online_school.models.model_enum.ResourceType;
import com.online_school.models.AddMaterials;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.online_school.repositories.AddMaterialsRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AddMaterialsService {


    private final AddMaterialsRepository addMaterialsRepository;

    public AddMaterialsService(AddMaterialsRepository addMaterialsRepository) {
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

    public void savePerson(AddMaterials addMaterials) {
        addMaterialsRepository.save(addMaterials);
    }


}
