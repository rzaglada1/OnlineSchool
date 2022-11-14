package services;


import models.Model;
import models.Student;
import repositories.StudentRepository;

public class StudentService {

    public Student createStudent() {
        return new Student();
    }

    public Student createStudent(String name) {
        return new Student(name);
    }

    public void printObjectsRepository(StudentRepository studentRepository) {

        Model[] studentArray = studentRepository.getAll();

        for (Model student : studentArray) {
            if (student != null) {
                System.out.println("Name student - \"" + student.getName() + "\". Student id = " + student.getID());
            }


        }
    }

}
