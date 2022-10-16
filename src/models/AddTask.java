package models;

public class AddTask {
    private Integer idAddTask;
    private String nameAddTask;
    public static Long CREATE_COUNT_ADD_TASK = 0L;

    public AddTask() {
        CREATE_COUNT_ADD_TASK++;
    }

}
