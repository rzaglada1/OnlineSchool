package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class Course implements Model, Serializable {

    private  Integer ID;
    private String name;
    private  LocalDateTime creationDate;

    private Person person;
    private Lecture lecture;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name, LocalDateTime creationDate ) {
        this.ID = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Optional<Person> getPerson() {
        return Optional.ofNullable(person);
    }

    public Optional<Lecture> getLecture() {
        return Optional.ofNullable(lecture);
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + getID() +
                ", nameCourse='" + getName() + '\'' +
                ", person=" + getPerson().or(Optional::empty) +
                ", lecture=" + getLecture().or(Optional::empty) +
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
        return Objects.equals(ID, course.ID) && Objects.equals(name, course.name) && Objects.equals(creationDate, course.creationDate) && Objects.equals(person, course.person) && Objects.equals(lecture, course.lecture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, creationDate, person, lecture);
    }
}
