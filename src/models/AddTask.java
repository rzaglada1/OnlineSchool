package models;

public class AddTask {
    private final Integer idAddTask;
    private String nameAddTask;
    private static Integer CREATE_COUNT_ADD_TASK = 0;

    public AddTask() {
        CREATE_COUNT_ADD_TASK++;
        this.idAddTask = CREATE_COUNT_ADD_TASK;
    }

    public AddTask(String nameAddTask) {
        CREATE_COUNT_ADD_TASK++;
        this.idAddTask = CREATE_COUNT_ADD_TASK;
        this.nameAddTask = nameAddTask;
    }

    public Integer getIdAddTask() {
        return idAddTask;
    }

    public String getNameAddTask() {
        return nameAddTask;
    }

    public static Integer getCreateCountAddTask() {
        return CREATE_COUNT_ADD_TASK;
    }

    @Override
    public String toString() {
        return "AddTask{" +
                "idAddTask=" + idAddTask +
                ", nameAddTask='" + nameAddTask + '\'' +
                '}';
    }
}
