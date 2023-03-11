package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Course implements Model, Serializable {

    private final Integer ID;
    private String name;
    private final LocalDateTime CreationDate;

    private Person person;
    private Lecture lecture;
    private Homework homework;
    private static Integer createCount = 0;

    public Course() {
        this.ID = createCount++;
        CreationDate = LocalDateTime.now();
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

    public Optional<Person> getPerson() {
        return Optional.ofNullable(person);
    }

    public Optional<Lecture> getLecture() {
        return Optional.ofNullable(lecture);
    }

    public Optional<Homework> getHomework() {
        return Optional.ofNullable(homework);
    }


    public static Integer getCreateCount() {
        return createCount;
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

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + getID() +
                ", nameCourse='" + getName() + '\'' +
                ", person=" + getPerson().or(Optional::empty) +
                ", lecture=" + getLecture().or(Optional::empty) +
                ", homework=" + getHomework().or(Optional::empty) +

                '}';
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
    public Optional<Course> getCourse() {
        return Optional.of(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(ID, course.ID) && Objects.equals(name, course.name) && Objects.equals(person
                , course.person) && Objects.equals(lecture, course.lecture) && Objects.equals(homework, course.homework);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, person, lecture, homework);
    }
}
