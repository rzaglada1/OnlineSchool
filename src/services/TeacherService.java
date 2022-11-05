package services;

import models.Teacher;
import repositories.TeacherRepository;

public class TeacherService {

    public Teacher createTeacher() {
        return new Teacher();
    }

    public Teacher createTeacher(String nameTeacher) {
        return new Teacher(nameTeacher);
    }

    public void printObjectsRepository(TeacherRepository teacherRepository) {

        Teacher[] teacherArray = teacherRepository.getTeacherRepository();

        for (Teacher teacher : teacherArray) {
            if (teacher == null) {
                break;
            }
            System.out.println("Name teacher - \"" + teacher.getNameTeacher() + "\". Teacher id = " + teacher.getIdTeacher());

        }
    }
}
