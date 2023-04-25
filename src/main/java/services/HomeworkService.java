package services;

import models.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.HomeWorkRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class HomeworkService {

    private HomeWorkRepository homeWorkRepository;

    public HomeworkService() {
    }


    @Autowired
    public void setHomeWorkRepository(HomeWorkRepository homeWorkRepository) {
        this.homeWorkRepository = homeWorkRepository;
    }

    public List<Homework> getAllHomework() {
        return homeWorkRepository.getRepository();
    }

    public void printRepository() {
        getAllHomework().forEach(System.out::println);
    }

    public Optional<Homework> getHomeworkById(long id) {
        return homeWorkRepository.getById(id);
    }

    public List<Homework> sortedHomeworkByName() {
        return homeWorkRepository.sortedByName();
    }

    public List<Homework> getHomeworkByLectureId(long lectureId) {
        return getAllHomework().stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get() == lectureId).collect(Collectors.toList());
    }

    public void saveHomework(Homework homework) {
        homeWorkRepository.saveHomeworkToRepository(homework);
    }
}
