package repositories;


import models.Homework;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.HomeworkService;
import java.util.*;
import java.util.stream.Collectors;

public class HomeWorkRepository implements Repository<Homework> {
//    {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        LectureRepository lectureRepository = context.getBean("lectureRepository", LectureRepository.class);
//
//        repository = new ArrayList<>();
//        try {
//
//            getRepository().add(new HomeworkService()
//                    .create(
//                            "homeworkLecture"
//                            , lectureRepository.getById(0).orElseThrow()
//                    ));
//            getRepository().add(new HomeworkService()
//                    .create(
//                            "homeworkLecture"
//                            , lectureRepository.getById(1).orElseThrow()
//                    ));
//
//            getRepository().add(new HomeworkService()
//                    .create(
//                            "homeworkLecture"
//                            , lectureRepository.getById(1).orElseThrow()
//                    ));
//            getRepository().add(new HomeworkService()
//                    .create(
//                            "homeworkLecture"
//                            , lectureRepository.getById(2).orElseThrow()
//                    ));
//        } catch (NoSuchElementException e) {
//            e.getStackTrace();
//        }
//    }


    private static HomeWorkRepository instance;
    private final List<Homework> repository = new ArrayList<>();

    private HomeWorkRepository() {

    }

    public static HomeWorkRepository getInstance() {
        if (instance == null) {
            instance = new HomeWorkRepository();
        }
        return instance;
    }

    @Override
    public List<Homework> getRepository() {
        return repository;
    }


    public void printRepository() {
        repository.forEach(System.out::println);
    }

    @Override
    public Optional<Homework> getById(Integer id) {
        return repository.stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Homework> sortedByName() {
        return repository.stream()
                .sorted(Comparator.comparing(Homework::getName))
                .collect(Collectors.toList());
    }

    public List<Homework> getHomeworkByLectureId(int lectureId) {
        return repository.stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get()  == lectureId).collect(Collectors.toList());
    }

}
