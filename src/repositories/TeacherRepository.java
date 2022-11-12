package repositories;


import models.Teacher;

public class TeacherRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Teacher[] repository = new Teacher[capacity];

    public void add(Teacher teacher) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = teacher;
                return;
            }
        }
        expandArray();
        repository[capacityOld] = teacher;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Teacher[] tmpRepository = new Teacher[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    public Teacher[] getRepository() {
        return repository;
    }
}
