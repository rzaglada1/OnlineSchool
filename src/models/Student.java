package models;

public class Student {
    private final Integer id;
    private String name;
    private static Integer CREATE_COUNT = 0;

    public Student() {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
    }

    public Student(String name) {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
        this.name = name;
    }

    public Integer getId () {
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
        return "Student{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
