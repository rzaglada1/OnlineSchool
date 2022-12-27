package models;

import java.util.Objects;

public class Course extends Model {

    private Integer ID;
    private String name;

    private Person person;
    private Lecture lecture;
    private Homework homework;
    private Task task;
    private static Integer CREATE_COUNT = 0;

    public Course() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Course(String name) {
        this();
        setName(name);
    }

    public Course(String name, Person person, Lecture lecture) {
        this(name);
        this.person = person;
        this.lecture = lecture;
    }

    public Person getPerson() {
        return person;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public Homework getHomework() {
        return homework;
    }

    public Task getAddTask() {
        return task;
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public void setAddTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + getID() +
                ", nameCourse='" + getName() + '\'' +
                ", person=" + person +
                ", lecture=" + lecture +
                ", homework=" + homework +
                ", addTask=" + task +
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
        Course course = (Course) o;
        return Objects.equals(ID, course.ID) && Objects.equals(name, course.name) && Objects.equals(person, course.person) && Objects.equals(lecture, course.lecture) && Objects.equals(homework, course.homework) && Objects.equals(task, course.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, person, lecture, homework, task);
    }
}
