package services;

import models.AddTask;
import repositories.AddTaskRepository;

public class AddTaskService {

    public AddTask createAddTask() {
        return new AddTask();
    }

    public AddTask createAddTask(String name) {
        return new AddTask(name);
    }

    public void printObjectsRepository(AddTaskRepository addTaskRepository) {

        AddTask[] addTasksArray = addTaskRepository.getRepository();

        for (AddTask addTask : addTasksArray) {
            if (addTask == null) {
                break;
            }
            System.out.println("Name addTask - \"" + addTask.getName () + "\". addTask id = " + addTask.getId ());

        }
    }
}
