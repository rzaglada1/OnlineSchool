package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Course implements Model, Serializable {

    private  Integer ID;
    private String name;
    private  LocalDateTime creationDate;

    private List<Person> persons;
    private List<Lecture> lectures;

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

    public List<Person> getPersons() {
        return persons;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }


    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
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
                ", persons=" + getPersons() +
                ", lecture=" + getLectures() +
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

    public Optional<Course> getCourse() {
        return Optional.of(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(ID, course.ID) && Objects.equals(name, course.name) && Objects.equals(creationDate, course.creationDate) && Objects.equals(persons, course.persons) && Objects.equals(lectures, course.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, creationDate, persons, lectures);
    }
}
