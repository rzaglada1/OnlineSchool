package models;

public class Homework extends Model {
    private static Integer CREATE_COUNT = 0;

    public Homework() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Homework(String name) {
        this();
        setName(name);
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }


    @Override
    public String toString() {
        return "Homework{" +
                "id =" + getID() +
                ", name ='" + getName() + '\'' +
                '}';
    }
}
