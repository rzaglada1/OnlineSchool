package repositories;

import models.Homework;

public class HomeworkRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Homework[] repository = new Homework[capacity];

    public void add(Homework homework) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = homework;
                return;
            }
        }
        expandArray();
        repository[capacityOld] = homework;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Homework[] tmpRepository = new Homework[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    public Homework[] getRepository() {
        return repository;
    }
}
