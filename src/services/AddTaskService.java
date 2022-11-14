package services;

import models.AddTask;
import models.Model;
import repositories.AddTaskRepository;

public class AddTaskService {

    public AddTask createAddTask() {
        return new AddTask();
    }

    public AddTask createAddTask(String name) {
        return new AddTask(name);
    }

    public void printObjectsRepository(AddTaskRepository addTaskRepository) {

        Model[] addTasksArray = addTaskRepository.getAll();

        for (Model addTask : addTasksArray) {
            if (addTask != null) {
                System.out.println("Name addTask - \"" + addTask.getName() + "\". addTask id = " + addTask.getID());
            }


        }
    }
}
