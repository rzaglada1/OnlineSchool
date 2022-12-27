package models;

import java.util.Objects;

public class Task extends Model {
    private static Integer CREATE_COUNT = 0;

    private Integer ID;
    private String name;


    public Task() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Task(String name) {
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



    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(ID, task.ID) && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name);
    }
}
