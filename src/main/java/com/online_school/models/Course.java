package com.online_school.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "courses", schema = "online_school")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Model, Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "create_date")
    private LocalDateTime creationDate;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    public Course() {
        creationDate = LocalDateTime.now();
    }


    public Course(Long ID, String name, LocalDateTime creationDate) {
        this();
        this.ID = ID;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Course(String name) {
        this();
        this.name = name;
    }

    public Course(String name, Person person) {
        this();
        this.name = name;
        this.persons.add(person);
    }

    public Course(long id, String name, LocalDateTime creationDate) {
        this();
        this.ID = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public void setID(long ID) {
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
                '}';
    }

    @Override
    public long getID() {
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
