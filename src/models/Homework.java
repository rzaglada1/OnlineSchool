package models;

import java.util.Objects;

public class Homework extends Model {

    private Integer ID;
    private String name;

    private Model course;
    private Integer lectureID;
    private Task task;

    private static Integer createCount = 0;

    public Homework() {
        createCount++;
        setID(createCount);
    }

    public Homework(String name) {
        this();
        setName(name);
    }

    public Homework(String name, Model course) {
        this(name);
        this.course = course;
    }

    public Homework(String name, Integer lectureID) {
        this(name);
        setLectureID(lectureID);
    }

    public static Integer getCreateCount() {
        return createCount;
    }

    public Integer getLectureID() {
        return lectureID;
    }

    public void setLectureID(Integer lectureID) {
        this.lectureID = lectureID;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Model getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lectureID=" + lectureID +
                ", task=" + task +
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
        Homework homework = (Homework) o;
        return Objects.equals(ID, homework.ID) && Objects.equals(name, homework.name) && Objects.equals(lectureID, homework.lectureID) && Objects.equals(task, homework.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lectureID, task);
    }
}
