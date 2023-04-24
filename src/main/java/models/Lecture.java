package models;



import jakarta.persistence.*;
import utils.log.Log;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Table(name = "lectures", schema = "online_school")
public class Lecture implements Model, Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "create_date")
    private final LocalDateTime creationDate;
    @Column(name = "lecture_date")
    private LocalDateTime lectureDate;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddMaterials> addMaterialsList;
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Homework> homeworks;



    @Transient
    private final String DATE_LECTURE_FORMAT = "MMM d, EEEE HH:mm:ss";
    @Transient
    private final Locale locale = Locale.US;
    @Transient
    private long addMaterialsCount;
    @Transient
    private long idCourse;
    @Transient
    private long personID;


    public Lecture() {
        creationDate = LocalDateTime.now();
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


    public long getIdCourse() {
        return idCourse;
    }


    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }


    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    public Optional<Person> getPerson() {
        return Optional.ofNullable(person);
    }

    public void setPerson(Person person) {
        this.person = person;
        this.personID = person.getID();
    }


    public List<AddMaterials> getAddMaterialsList() {
        return addMaterialsList;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void setHomework(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
    }



    public void setAddMaterialsList(List<AddMaterials> addMaterialsList) {
        this.addMaterialsList = addMaterialsList;
    }


    public void setID(long ID) {
        this.ID = ID;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setAddMaterialsCount(long addMaterialsCount) {
        this.addMaterialsCount = addMaterialsCount;
    }

    @Override
    public String toString() {
        if (person != null) {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    ", idLecture=" + getID() +
//                    ", personID=" + personID +
//                    ", personName=" + person.getName() +
//                    ", personLastName=" + person.getLastName() +
//                    ", personRole=" + person.getRole() +
//                    ", idCourse=" + idCourse +
//                    ", courseName=" + course.getName() +
                    ", dateCreated= " + getCreationDate() +
                    ", dateStartLecture= " + formatDate(getLectureDate(), DATE_LECTURE_FORMAT, locale) +
                    '}' + '\n';
        } else {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    ", idLecture=" + getID() +
                    ", idCourse=" + idCourse +
//                    ", courseName=" + course.getName() +
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
        Lecture lecture = (Lecture) o;
        return addMaterialsCount == lecture.addMaterialsCount && idCourse == lecture.idCourse && personID == lecture.personID && Objects.equals(ID, lecture.ID) && Objects.equals(name, lecture.name) && Objects.equals(DATE_LECTURE_FORMAT, lecture.DATE_LECTURE_FORMAT) && Objects.equals(locale, lecture.locale) && Objects.equals(creationDate, lecture.creationDate) && Objects.equals(lectureDate, lecture.lectureDate) && Objects.equals(addMaterialsList, lecture.addMaterialsList) && Objects.equals(homeworks, lecture.homeworks) && Objects.equals(course, lecture.course) && Objects.equals(person, lecture.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, DATE_LECTURE_FORMAT, locale, creationDate, lectureDate, addMaterialsList, addMaterialsCount, homeworks, course, idCourse, personID, person);
    }
}
