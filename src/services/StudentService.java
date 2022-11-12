package services;


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

        Student[] studentArray = studentRepository.getRepository();

        for (Student student : studentArray) {
            if (student == null) {
                break;
            }
            System.out.println("Name student - \"" + student.getName () + "\". Student id = " + student.getId () );

        }
    }

}
