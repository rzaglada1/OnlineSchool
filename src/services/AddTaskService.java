package services;

import models.Task;

public class AddTaskService {

    public Task create() {
        return new Task();
    }

    public Task create(String name) {
        return new Task(name);
    }

}
