package services;

import models.Teacher;
import repositories.TeacherRepository;

public class TeacherService {

    public Teacher createTeacher() {
        return new Teacher();
    }

    public Teacher createTeacher(String name) {
        return new Teacher(name);
    }

    public void printObjectsRepository(TeacherRepository teacherRepository) {

        Teacher[] teacherArray = teacherRepository.getRepository();

        for (Teacher teacher : teacherArray) {
            if (teacher == null) {
                break;
            }
            System.out.println("Name teacher - \"" + teacher.getName () + "\". Teacher id = " + teacher.getId () );

        }
    }
}
