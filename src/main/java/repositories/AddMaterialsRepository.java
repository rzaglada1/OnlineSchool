package repositories;

import models.AddMaterials;
import models.model_enum.ResourceType;
import services.AddMaterialsService;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddMaterialsRepository implements Repository<AddMaterials> {

    {
        LectureRepository lectureRepository = LectureRepository.getInstance();
        repository = new ArrayList<>();
        try {
            getRepository().add(new AddMaterialsService()
                    .create("Text book", ResourceType.BOOK
                            , lectureRepository.getById(0).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterialsService()
                    .create("Text book", ResourceType.BOOK
                            , lectureRepository.getById(3).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterialsService()
                    .create("Text url", ResourceType.URL
                            , lectureRepository.getById(0).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterialsService()
                    .create("Text video", ResourceType.VIDEO
                            , lectureRepository.getById(3).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterialsService()
                    .create("Text video", ResourceType.VIDEO
                            , lectureRepository.getById(2).orElseThrow(NoSuchElementException::new)));
        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }


    private static AddMaterialsRepository instance;
    private final List<AddMaterials> repository;

    private AddMaterialsRepository() {

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
        return getRepository().stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get() == lectureId).collect(Collectors.toList());
    }

    public void printAddMaterialsByLectureId(int lectureID) {
        Predicate<AddMaterials> filterByLectureID = element -> element.getLectureID().isPresent()
                && element.getLectureID().get() == lectureID;
        repository.stream().filter(filterByLectureID).forEach(System.out::println);
    }


    public void printAddMaterialsGroupByLecture() {
        repository.stream()
                .collect(Collectors.groupingBy(AddMaterials::getLecture))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public Map<ResourceType, Long> countCategory () {
        return repository.stream().collect(
                Collectors.groupingBy(AddMaterials::getResourceType, Collectors.counting()));

    }
}
