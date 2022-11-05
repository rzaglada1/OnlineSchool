package services;

import models.AddTask;
import repositories.AddTaskRepository;

public class AddTaskService {

    public AddTask createAddTask() {
        return new AddTask();
    }

    public AddTask createAddTask(String nameAddTask) {
        return new AddTask(nameAddTask);
    }

    public void printObjectsRepository(AddTaskRepository addTaskRepository) {

        AddTask[] addTasksArray = addTaskRepository.getAddTaskRepository();

        for (AddTask addTask : addTasksArray) {
            if (addTask == null) {
                break;
            }
            System.out.println("Name addTask - \"" + addTask.getNameAddTask() + "\". addTask id = " + addTask.getIdAddTask());

        }
    }
}
