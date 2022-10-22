package services;

import models.Homework;

public class HomeworkService {

    public Homework createHomework() {
        return new Homework();
    }

    public Homework createHomework(String nameHomework) {
        return new Homework(nameHomework);
    }
}
