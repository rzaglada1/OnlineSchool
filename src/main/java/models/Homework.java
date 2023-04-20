package models;

import utils.log.Log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Homework implements Model, Serializable {

    private final long ID;
    private String name;
    private final LocalDateTime CreationDate;

    private final String DATE_FORMAT = "MMM d,  HH:mm";
    private final Locale locale = Locale.US;
    private LocalDateTime deadlineDate;

    private Course course;
    private Lecture lecture;
    private long lectureID;


    private static Integer createCount = 0;

    public Homework(){
        this.ID = createCount++;
        CreationDate = LocalDateTime.now();
    }

    public Homework(String name, Lecture lecture) {
        this();
        this.name = name;
        this.lectureID = lecture.getID();
        this.lecture = lecture;
        this.deadlineDate = lecture.getLectureDate().plusDays(1).withHour(12).withMinute(0);

        try {
            course = lecture.getCourse().orElseThrow(NullPointerException::new);
        } catch (NullPointerException e ) {
            Log.error("Model Homework", "NullPointerException", e.getStackTrace());
        }
    }


    public static Integer getCreateCount() {
        return createCount;
    }

    public Optional<Long> getLectureID() {
        return Optional.ofNullable(lectureID);
    }

    public void setLectureID(long lectureID) {
        this.lectureID = lectureID;
    }

    public Optional<Course> getCourse() {
        return Optional.ofNullable(course);
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
    }

    public String formatDate(LocalDateTime dateTime, String strFormat, Locale locale) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(strFormat, locale);
        return dateTime.format(df);
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lectureID=" + lectureID +
                ", courseName = " + getCourse().stream().filter(Objects::nonNull).toList() +
                ", deadline =" + formatDate(deadlineDate, DATE_FORMAT, locale) +

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(ID, homework.ID) && Objects.equals(name, homework.name) && Objects.equals(lectureID, homework.lectureID) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lectureID);
    }
}
