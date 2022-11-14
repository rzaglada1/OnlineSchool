package services;

import models.Model;
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

        Model[] teacherArray = teacherRepository.getAll();

        for (Model teacher : teacherArray) {
            if (teacher != null) {
                System.out.println("Name teacher - \"" + teacher.getName() + "\". Teacher id = " + teacher.getID());
            }


        }
    }
}
