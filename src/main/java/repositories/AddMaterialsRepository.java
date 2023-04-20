package repositories;

import models.AddMaterials;
import models.model_enum.ResourceType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import services.AddMaterialsService;

import java.util.*;
import java.util.stream.Collectors;

public class AddMaterialsRepository implements Repository<AddMaterials>, InitializingBean {


    private LectureRepository lectureRepository;
    private AddMaterialsService addMaterialsService;


    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Autowired
    public void setAddMaterialsService(AddMaterialsService addMaterialsService) {
        this.addMaterialsService = addMaterialsService;
    }

    private final List<AddMaterials> repository = new ArrayList<>();


    @Override
    public List<AddMaterials> getRepository() {
        return repository;
    }


    @Override
    public Optional<AddMaterials> getById(long id) {
        return repository.stream().filter(element -> element.getID() == id).findAny();
    }

    @Override
    public List<AddMaterials> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(AddMaterials::getName))
                .collect(Collectors.toList());
    }


    @Override
    public void afterPropertiesSet() {
        //creating materials
        try {
            getRepository().add(new AddMaterials("Text book", ResourceType.BOOK
                    , lectureRepository.getById(1).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterials("Text book", ResourceType.BOOK
                    , lectureRepository.getById(3).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterials("Text url", ResourceType.URL
                    , lectureRepository.getById(1).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterials("Text video", ResourceType.VIDEO
                    , lectureRepository.getById(3).orElseThrow(NoSuchElementException::new)));
            getRepository().add(new AddMaterials("Text video", ResourceType.VIDEO
                    , lectureRepository.getById(2).orElseThrow(NoSuchElementException::new)));


        } catch (NoSuchElementException e) {
            e.getStackTrace();
        }
    }
}
