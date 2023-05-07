package com.online_school.models;

import com.online_school.models.model_enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons", schema = "online_school")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person implements Model, Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long ID;
    @Column(name = "name")
    @NotBlank(message = "{message.person-notBlank}")
    @Size(min = 2, max = 50, message = "{message.person-size}")
    private String name;
    @Column(name = "create_date")
    private final LocalDateTime CreationDate;
    @Column(name = "last_name")
    @NotBlank(message = "{message.person-notBlank}")
    @Size(min = 2, max = 50, message = "{message.person-size}")
    private String lastName;
    @Column(name = "phone")
    @NotBlank(message = "{message.person-notBlank}")
    @Size(min = 2, max = 50, message = "{message.person-size}")
    private String phone;
    @Column(name = "email")
    @NotBlank(message = "{message.person-notBlank}")
    @Size(min = 2, max = 50, message = "{message.person-size}")
    @Email(message = "{message.person-email}")
    private String email;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "courses_persons"
            , joinColumns = @JoinColumn(name = "person_id")
            , inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lecture> lectures;

    @Transient
    private long courseID;


    public Person(long ID, String name, LocalDateTime creationDate, String lastName
            , String phone, String email, Role role, List<Course> courses) {
        this();
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.courses = courses;
    }


    public Person() {
        CreationDate = LocalDateTime.now();
    }

    public Person(String[] personAttribute, Role role) {
        this();
        this.name = personAttribute[0];
        this.lastName = personAttribute[1];
        this.phone = personAttribute[2];
        this.email = personAttribute[3];
        this.role = role;

    }

    public Person(String[] personAttribute, Role role, List<Lecture> lecture) {
        this();
        this.name = personAttribute[0];
        this.lastName = personAttribute[1];
        this.phone = personAttribute[2];
        this.email = personAttribute[3];
        this.role = role;
        this.lectures = lectures;
    }


    public void setID(long ID) {
        this.ID = ID;
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", personID=" + getID() +
                ", role=" + role +
                ", Name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}' + '\n';
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return courseID == person.courseID && Objects.equals(ID, person.ID) && Objects.equals(name, person.name) && Objects.equals(CreationDate, person.CreationDate) && Objects.equals(lastName, person.lastName) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email) && role == person.role && Objects.equals(courses, person.courses) && Objects.equals(lectures, person.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, CreationDate, lastName, phone, email, role, courses, lectures, courseID);
    }
}
