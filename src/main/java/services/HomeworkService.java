package services;

import models.Homework;
import models.Lecture;


public class HomeworkService {

    public Homework create(String name, Lecture lecture) {
        return new Homework(name, lecture);
    }

}
