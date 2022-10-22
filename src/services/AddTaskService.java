package services;

import models.AddTask;

public class AddTaskService {

    public AddTask createAddTask() {
        return new AddTask();
    }

    public AddTask createAddTask(String nameAddTask) {
        return new AddTask(nameAddTask);
    }
}
