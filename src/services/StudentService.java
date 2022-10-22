package services;

import models.Student;

public class StudentService {

    public Student createStudent() {
        return new Student();
    }

    public Student createStudent(String nameStudent) {
        return new Student(nameStudent);
    }

}
