package services;

import models.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.HomeWorkRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeworkService {

    private HomeWorkRepository homeWorkRepository;

    public HomeworkService() {
    }


    @Autowired
    public void setHomeWorkRepository(HomeWorkRepository homeWorkRepository) {
        this.homeWorkRepository = homeWorkRepository;
    }

    public List<Homework> getAllHomework() {
        return homeWorkRepository.findAll();
    }

    public void printRepository() {
        getAllHomework().forEach(System.out::println);
    }

    public Optional<Homework> getHomeworkById(long id) {
        return homeWorkRepository.findById(id);
    }

    public List<Homework> sortedHomeworkByName() {
        Sort sort = Sort.by("name").ascending();
        return homeWorkRepository.findAll(sort);
    }

    public List<Homework> getHomeworkByLectureId(long lectureId) {
        return getAllHomework().stream()
                .filter(element -> element.getLectureID().isPresent()
                        && element.getLectureID().get() == lectureId).collect(Collectors.toList());
    }

    @Transactional
    public void saveHomework(Homework homework) {
        homeWorkRepository.save(homework);
    }
}
