package models;

public class Student extends Model {
    private static Integer CREATE_COUNT = 0;

    public Student() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Student(String name) {
        this();
        setName(name);
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id = " + getID() +
                ", name = '" + getName() + '\'' +
                '}';
    }
}
