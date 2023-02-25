package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Task implements Model, Serializable {
    private static Integer createCount = 0;

    private final Integer ID;
    private String name;
    private final LocalDateTime CreationDate;


    private Course course;


    public Task() {
        this.ID = createCount++;
        CreationDate = LocalDateTime.now();
    }

    public Task(String name) {
        this();
        setName(name);
    }

    public Task(String name, Course course) {
        this(name);
        this.course = course;
    }


    public static Integer getCreateCount() {
        return createCount;
    }

    public Optional<Course> getCourse() {
        return Optional.ofNullable(course);
    }

    @Override
    public String toString() {
        return "AddTask{" +
                "id=" + getID() +
                ", name='" + getName() + '\'' +
                '}';
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }


    @Override
    public Integer getID() {
        return ID;
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
