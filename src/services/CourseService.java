package services;

import models.Course;
import models.Lecture;
import models.Student;
import models.Teacher;

public class CourseService {

    public Course createCurse() {
        return new Course();
    }

    public Course createCurse(String nameCourse) {
        return new Course(nameCourse);
    }

    public Course createCurse(String nameCourse, Teacher teacher, Student student, Lecture lecture) {
        return new Course(nameCourse, teacher, student,lecture);
    }
}
