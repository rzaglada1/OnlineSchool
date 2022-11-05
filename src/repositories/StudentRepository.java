package repositories;

import models.Student;

public class StudentRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Student[] studentRepository = new Student[capacity];

    public void add(Student student) {
        //System.out.println("studentRepository.length = " + studentRepository.length);
        for (int i = 0; i < studentRepository.length; i++) {
            if (studentRepository[i] == null) {
                studentRepository[i] = student;
                //System.out.println("added in array");
                return;
            }
        }
        expandArray();
        System.out.println("expanded array to " + studentRepository.length);
        studentRepository[capacityOld] = student;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Student[] tmpStudentRepository = new Student[capacity];
        System.arraycopy(studentRepository, 0, tmpStudentRepository, 0, capacityOld);
        studentRepository = tmpStudentRepository;
    }

    public Student[] getStudentRepository() {
        return studentRepository;
    }
}
