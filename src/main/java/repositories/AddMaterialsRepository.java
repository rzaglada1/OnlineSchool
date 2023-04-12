package repositories;

import models.AddMaterials;
import models.model_enum.ResourceType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.stream.Collectors;

public class AddMaterialsRepository implements Repository<AddMaterials> {


//    {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        LectureRepository lectureRepository = context.getBean("lectureRepository", LectureRepository.class);
//
//        repository = new ArrayList<>();
//        try {
//            getRepository().add(new AddMaterials ("Text book", ResourceType.BOOK
//                            , lectureRepository.getById(0).orElseThrow(NoSuchElementException::new)));
//            getRepository().add(new AddMaterials ("Text book", ResourceType.BOOK
//                            , lectureRepository.getById(3).orElseThrow(NoSuchElementException::new)));
//            getRepository().add(new AddMaterials("Text url", ResourceType.URL
//                            , lectureRepository.getById(0).orElseThrow(NoSuchElementException::new)));
//            getRepository().add(new AddMaterials("Text video", ResourceType.VIDEO
//                            , lectureRepository.getById(3).orElseThrow(NoSuchElementException::new)));
//            getRepository().add(new AddMaterials("Text video", ResourceType.VIDEO
//                            , lectureRepository.getById(2).orElseThrow(NoSuchElementException::new)));
//        } catch (NoSuchElementException e) {
//            e.getStackTrace();
//        }
//    }

    private final List<AddMaterials> repository = new ArrayList<>();;


    @Override
    public List<AddMaterials> getRepository() {
        return repository;
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


}
