package models;

import models.model_enum.Role;
import utils.RegexUtil;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person implements Model, Serializable {

    RegexUtil regexUtil;

    public void setRegexUtil(RegexUtil regexUtil) {
        this.regexUtil = regexUtil;
    }

    private final Integer ID;
    private String name;
    private final LocalDateTime CreationDate;

    private static Integer createCount = 0;


    private List<Course> courses = new ArrayList<>();
    private Lecture lecture;
    private int courseID;
    private Role role;


    private String lastName;
    private String phone;
    private String email;

    public Person() {
        this.ID = createCount++;
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

    public Person(String[] personAttribute, Role role, Course course) {
        this();
        this.name = personAttribute[0];
        this.lastName = personAttribute[1];
        this.phone = personAttribute[2];
        this.email = personAttribute[3];
        this.role = role;
        this.courses.add(course);
    }

    public Person(String[] personAttribute, Role role, Lecture lecture) {
        this();
        this.name = personAttribute[0];
        this.lastName = personAttribute[1];
        this.phone = personAttribute[2];
        this.email = personAttribute[3];
        this.role = role;
        this.lecture = lecture;
    }


    public static Integer getCreateCount() {
        return createCount;
    }

    public int getCourseID() {
        return courseID;
    }

    public Role getRole() {
        return role;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }


    public boolean setLastName(String lastName) {
        if (regexUtil.isCorrect(lastName, RegexUtil.REGEX_LAST_NAME)) {
            this.lastName = lastName;
            return true;
        }
        return false;

    }

    public boolean setPhone(String phone) {
        if (regexUtil.isCorrect(phone, RegexUtil.REGEX_PHONE)) {
            this.phone = phone;
            return true;
        }
        return false;

    }

    public boolean setEmail(String email) {
        if (regexUtil.isCorrect(email, RegexUtil.REGEX_EMAIL)) {
            this.email = email;
            return true;
        }
        return false;

    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lecture=" + lecture +
                ", courses=" + courses +
                ", personID=" + getID() +
                ", role=" + role +
                ", Name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}' + '\n';
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
        Person person = (Person) o;
        return courseID == person.courseID && Objects.equals(ID, person.ID) && Objects.equals(name, person.name) && Objects.equals(CreationDate, person.CreationDate) && Objects.equals(courses, person.courses) && Objects.equals(lecture, person.lecture) && role == person.role && Objects.equals(lastName, person.lastName) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, CreationDate, courses, lecture, courseID, role, lastName, phone, email);
    }
}
