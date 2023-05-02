package com.online_school.services;

import com.online_school.models.Homework;
import com.online_school.repositories.HomeWorkRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeworkService {

    private final HomeWorkRepository homeWorkRepository;

    public HomeworkService(HomeWorkRepository homeWorkRepository) {
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

    public void saveHomework(Homework homework) {
        homeWorkRepository.save(homework);
    }
}
