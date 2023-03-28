package services;


import models.Course;
import models.Lecture;
import models.Person;

import java.time.LocalDateTime;


public class LectureService {

    public Lecture create() {
        return new Lecture();
    }



    public Lecture create(String name, Course course, LocalDateTime lectureDate, Person person) {
        return new Lecture(name, course, lectureDate, person);
    }


}
