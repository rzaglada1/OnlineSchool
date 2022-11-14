package models;

public class Teacher extends Model {

    private static Integer CREATE_COUNT = 0;

    public Teacher() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Teacher(String name) {
        this();
        setName(name);
    }


    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id = " + getID() +
                ", name ='" + getName() + '\'' +
                '}';
    }
}
