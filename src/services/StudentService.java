package services;


import models.Student;
import repositories.StudentRepository;

public class StudentService {

    public Student createStudent() {
        return new Student();
    }

    public Student createStudent(String nameStudent) {
        return new Student(nameStudent);
    }

    public void printObjectsRepository(StudentRepository studentRepository) {

        Student[] studentArray = studentRepository.getStudentRepository();

        for (Student student : studentArray) {
            if (student == null) {
                break;
            }
            System.out.println("Name student - \"" + student.getNameStudent() + "\". Student id = " + student.getIdStudent());

        }
    }

}
