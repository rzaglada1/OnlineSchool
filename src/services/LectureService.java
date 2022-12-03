package services;


import models.Lecture;
import models.Model;


public class LectureService {

    public Lecture create() {
        return new Lecture();
    }

    public Lecture create(String name) {
        return new Lecture(name);
    }


    public Lecture create(String name, Model course) {
        return new Lecture(name, course);
    }


}
