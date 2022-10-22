package services;

import models.Teacher;

public class TeacherService {

    public Teacher createTeacher() {
        return new Teacher();
    }

    public Teacher createTeacher(String nameTeacher) {
        return new Teacher(nameTeacher);
    }
}
