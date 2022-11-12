package services;

import models.Homework;
import repositories.HomeworkRepository;

public class HomeworkService {

    public Homework createHomework() {
        return new Homework();
    }

    public Homework createHomework(String name) {
        return new Homework(name);
    }

    public void printObjectsRepository(HomeworkRepository homeworkRepository) {

        Homework[] homeworkArray = homeworkRepository.getRepository();

        for (Homework homework : homeworkArray) {
            if (homework == null) {
                break;
            }
            System.out.println("Name homeWork - \"" + homework.getName () + "\". HomeWork id = " + homework.getId () );

        }
    }
}
