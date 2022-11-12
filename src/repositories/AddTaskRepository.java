package repositories;

import models.AddTask;

public class AddTaskRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private AddTask[] repository = new AddTask[capacity];

    public void add(AddTask addTask) {

        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = addTask;
                return;
            }
        }
        expandArray();

        repository[capacityOld] = addTask;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        AddTask[] tmpRepository = new AddTask[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    public AddTask[] getRepository() {
        return repository;
    }
}
