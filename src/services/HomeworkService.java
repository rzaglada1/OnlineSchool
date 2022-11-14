package services;

import models.Homework;
import models.Model;
import repositories.HomeworkRepository;

public class HomeworkService {

    public Homework createHomework() {
        return new Homework();
    }

    public Homework createHomework(String name) {
        return new Homework(name);
    }

    public void printObjectsRepository(HomeworkRepository homeworkRepository) {

        Model[] homeworkArray = homeworkRepository.getAll();

        for (Model homework : homeworkArray) {
            if (homework != null) {
                System.out.println("Name homeWork - \"" + homework.getName() + "\". HomeWork id = " + homework.getID());
            }


        }
    }
}
