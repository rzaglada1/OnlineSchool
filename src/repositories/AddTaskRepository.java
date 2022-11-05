package repositories;

import models.AddTask;

public class AddTaskRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private AddTask[] addTaskRepository = new AddTask[capacity];

    public void add(AddTask addTask) {
        //System.out.println("addTaskRepository.length = " + addTaskRepository.length);
        for (int i = 0; i < addTaskRepository.length; i++) {
            if (addTaskRepository[i] == null) {
                addTaskRepository[i] = addTask;
                //System.out.println("added in array");
                return;
            }
        }
        expandArray();
        System.out.println("expanded array to " + addTaskRepository.length);
        addTaskRepository[capacityOld] = addTask;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        AddTask[] tmpAddTaskRepository = new AddTask[capacity];
        System.arraycopy(addTaskRepository, 0, tmpAddTaskRepository, 0, capacityOld);
        addTaskRepository = tmpAddTaskRepository;
    }

    public AddTask[] getAddTaskRepository() {
        return addTaskRepository;
    }
}
