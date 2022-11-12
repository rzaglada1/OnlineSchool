package models;

public class Homework {
    private final Integer id;
    private String name;
    private static Integer CREATE_COUNT = 0;

    public Homework() {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
    }

    public Homework(String name) {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName () {
        return name;
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id =" + id +
                ", name ='" + name + '\'' +
                '}';
    }
}
