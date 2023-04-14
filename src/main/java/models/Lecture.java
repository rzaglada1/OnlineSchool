package models;



import services.HomeworkService;
import utils.log.Log;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lecture implements Model, Serializable {

    private HomeworkService homeworkService;

    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    private final Integer ID;
    private String name;

    private final String DATE_LECTURE_FORMAT = "MMM d, EEEE HH:mm:ss";
    private final Locale locale = Locale.US;
    private final LocalDateTime creationDate;
    private LocalDateTime lectureDate;
    private List<AddMaterials> addMaterialsList;
    private long addMaterialsCount;


    private List<Homework> homeworks;

    private static Integer createCount = 0;

    private Course course;
    private int idCourse;
    private int personID;
    private Person person;

    public Lecture() {
        this.ID = createCount++;
        //creationDate = LocalDateTime.now();
        creationDate = LocalDateTime.of(2023, 9, 19, 14, 5);

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

    public List<Homework> getHomework() {

        return homeworkService.getHomeworkByLectureId(this.ID);
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



    public void setAddMaterialsCount(long addMaterialsCount) {
        this.addMaterialsCount = addMaterialsCount;
    }

    @Override
    public String toString() {
        if (person != null) {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    ", idLecture=" + getID() +
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
                    ", idCourse=" + idCourse +
                    ", courseName=" + course.getName() +
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
        return addMaterialsCount == lecture.addMaterialsCount && idCourse == lecture.idCourse && personID == lecture.personID && Objects.equals(ID, lecture.ID) && Objects.equals(name, lecture.name) && Objects.equals(DATE_LECTURE_FORMAT, lecture.DATE_LECTURE_FORMAT) && Objects.equals(locale, lecture.locale) && Objects.equals(creationDate, lecture.creationDate) && Objects.equals(lectureDate, lecture.lectureDate) && Objects.equals(addMaterialsList, lecture.addMaterialsList) && Objects.equals(homeworks, lecture.homeworks) && Objects.equals(course, lecture.course) && Objects.equals(person, lecture.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, DATE_LECTURE_FORMAT, locale, creationDate, lectureDate, addMaterialsList, addMaterialsCount, homeworks, course, idCourse, personID, person);
    }
}
