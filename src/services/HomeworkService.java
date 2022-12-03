package services;

import models.Homework;
import models.Model;

public class HomeworkService {

    public Homework create() {
        return new Homework();
    }

    public Homework create(String name) {
        return new Homework(name);
    }

}
