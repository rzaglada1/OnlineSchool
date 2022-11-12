package models;

public class AddTask {
    private final Integer id;
    private String name;
    private static Integer CREATE_COUNT = 0;

    public AddTask() {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
    }

    public AddTask(String name) {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    @Override
    public String toString() {
        return "AddTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
