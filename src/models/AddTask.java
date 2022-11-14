package models;

public class AddTask extends Model {
    private static Integer CREATE_COUNT = 0;

    public AddTask() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public AddTask(String name) {
        this();
        setName(name);
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    @Override
    public String toString() {
        return "AddTask{" +
                "id=" + getID() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
