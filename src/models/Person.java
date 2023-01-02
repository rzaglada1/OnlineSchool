package models;

import models.model_enum.Role;
import utils.RegexUtil;

import java.util.Objects;


public class Person extends Model {

    private Integer ID;
    private String name;

    private static Integer CREATE_COUNT = 0;


    private int courseID;
    private Role role;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Person() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Person(String[] personAttribute, Role role) {
        this();
        this.firstName = personAttribute[0];
        this.lastName = personAttribute[1];
        this.phone = personAttribute[2];
        this.email = personAttribute[3];
        this.role = role;

    }

    public Person(String[] personAttribute, Role role, Model course) {
        this();
        this.firstName = personAttribute[0];
        this.lastName = personAttribute[1];
        this.phone = personAttribute[2];
        this.email = personAttribute[3];
        this.role = role;
        this.courseID = course.getID();
    }


    public static Integer getCreateCount() {
        return CREATE_COUNT;
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

    public String getFirstName() {
        return firstName;
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

    public boolean setFirstName(String firstName) {
        if (new RegexUtil().isCorrect(firstName, RegexUtil.REGEX_FIRST_NAME)) {
            this.firstName = firstName;
            return true;
        }
        return false;
    }

    public boolean setLastName(String lastName) {
        if (new RegexUtil().isCorrect(lastName, RegexUtil.REGEX_LAST_NAME)) {
            this.lastName = lastName;
            return true;
        }
        return false;

    }

    public boolean setPhone(String phone) {
        if (new RegexUtil().isCorrect(phone, RegexUtil.REGEX_PHONE)) {
            this.phone = phone;
            return true;
        }
        return false;

    }

    public boolean setEmail(String email) {
        if (new RegexUtil().isCorrect(email, RegexUtil.REGEX_EMAIL)) {
            this.email = email;
            return true;
        }
        return false;

    }


    @Override
    public String toString() {
        return "Person{" +
                "courseID=" + courseID +
                ", personID=" + getID() +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
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
        Person person = (Person) o;
        return courseID == person.courseID && Objects.equals(ID, person.ID) && Objects.equals(name, person.name) && role == person.role && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, courseID, role, firstName, lastName, phone, email);
    }
}
