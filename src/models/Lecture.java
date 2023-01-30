package models;

import java.util.Arrays;
import java.util.Objects;

public class Lecture extends Model {

    private Integer ID;
    private String name;

    private Homework[] homework;

    private static Integer createCount = 0;

    private Model course;
    private int idCourse;
    private int personID;
    private Person person;

    public Lecture() {
        createCount++;
        setID(createCount);
    }

    public Lecture(String name) {
        this();
        setName(name);
    }


    public Lecture(String name, Model course) {
        this(name);
        this.course = course;
        this.idCourse = course.getID();
    }


    public Lecture(String name, Model course, Model person) {
        this(name, course);
        this.idCourse = course.getID();
        this.personID = person.getID();
    }


    public Integer getIdCourse() {
        return idCourse;
    }

    public static Integer getCreateCount() {
        return createCount;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }


    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.personID = person.getID();
    }

    public Homework[] getHomework() {
        return homework;
    }

    public void setHomework(Homework[] homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        if (person != null) {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    ", idLecture=" + getID() +
                    ", arrayHomework=" + Arrays.toString(homework) +
                    ", personID=" + personID +
                    ", personName=" + person.getName() +
                    ", personLastName=" + person.getLastName() +
                    ", personRole=" + person.getRole() +
                    ", idCourse=" + idCourse +
                    ", courseName=" + course.getName() +
                    '}';
        } else {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    ", idLecture=" + getID() +
                    ", arrayHomework=" + Arrays.toString(homework) +
                    ", idCourse=" + idCourse +
                    ", courseName=" + course.getName() +
                    '}';
        }
    }

    public Model getCourse() {
        return course;
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
        Lecture lecture = (Lecture) o;
        return idCourse == lecture.idCourse && personID == lecture.personID && Objects.equals(ID, lecture.ID) && Objects.equals(name, lecture.name) && Arrays.equals(homework, lecture.homework) && Objects.equals(person, lecture.person);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ID, name, idCourse, personID, person);
        result = 31 * result + Arrays.hashCode(homework);
        return result;
    }
}
