package services;

import models.Homework;
import repositories.HomeworkRepository;

public class HomeworkService {

    public Homework createHomework() {
        return new Homework();
    }

    public Homework createHomework(String nameHomework) {
        return new Homework(nameHomework);
    }

    public void printObjectsRepository(HomeworkRepository homeworkRepository) {

        Homework[] homeworkArray = homeworkRepository.getHomeworkRepository();

        for (Homework homework : homeworkArray) {
            if (homework == null) {
                break;
            }
            System.out.println("Name homeWork - \"" + homework.getNameHomework() + "\". HomeWork id = " + homework.getIdHomeWork());

        }
    }
}
