package repositories;

import models.Student;

public class StudentRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Student[] repository = new Student[capacity];

    public void add(Student student) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = student;
                return;
            }
        }
        expandArray();
        repository[capacityOld] = student;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Student[] tmpRepository = new Student[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    public Student[] getRepository() {
        return repository;
    }
}
