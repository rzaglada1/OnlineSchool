package models;

import utils.log.Log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Lecture implements Model, Serializable {

    private final Integer ID;
    private String name;

    private final String DATE_LECTURE_FORMAT = "MMM d, EEEE HH:mm:ss";
    private final Locale locale = Locale.US;
    private final LocalDateTime CreationDate;
    private LocalDateTime lectureDate;


    private Homework[] homework;

    private static Integer createCount = 0;

    private Course course;
    private int idCourse;
    private int personID;
    private Person person;

    public Lecture() {
        this.ID = createCount++;
        CreationDate = LocalDateTime.now();

    }

    public Lecture(String name, Course course, LocalDateTime lectureDate, Person person) {
        this();
        setName(name);
        this.course = course;
        this.idCourse = course.getID();
        this.lectureDate = lectureDate;
        this.idCourse = course.getID();
        this.person = person;
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

    public Optional<Person> getPerson() {
        return Optional.ofNullable(person);
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

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
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
//                    ", dateCreated= " + formatDate(getCreationDate(), DATE_LECTURE_FORMAT, locale) +
                    ", dateCreated= " + getCreationDate() +
                    ", dateStartLecture= " + formatDate(getLectureDate(), DATE_LECTURE_FORMAT, locale) +
                    '}' + '\n';
        } else {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    ", idLecture=" + getID() +
                    ", arrayHomework=" + Arrays.toString(homework) +
                    ", idCourse=" + idCourse +
                    ", courseName=" + course.getName() +
//                    ", dateCreated= " + formatDate(getCreationDate(), DATE_LECTURE_FORMAT, locale) +
                    ", dateCreated= " + getCreationDate() +
                    ", dateStartLecture= " + formatDate(getLectureDate(), DATE_LECTURE_FORMAT, locale) +
                    '}' + '\n';
        }
    }



    public Optional<Course> getCourse() {
        return Optional.ofNullable(course);
    }

    public String formatDate(LocalDateTime dateTime, String strFormat, Locale locale) {
        String formatDate = "--";
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern(strFormat, locale);
            formatDate = dateTime.format(df);
        } catch (NullPointerException e) {
            Log.warning("On-line school", "convert date format error", e.getStackTrace());
        }

        return formatDate;
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
