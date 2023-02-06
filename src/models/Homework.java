package models;

import utils.log.Log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class Homework implements Model, Serializable {

    private final Integer ID;
    private String name;
    private final LocalDateTime CreationDate;

    private final String DATE_FORMAT = "MMM d,  HH:mm";
    private final Locale locale = Locale.US;
    private final LocalDateTime deadlineDate;

    private Course course;
    private Lecture lecture;
    private Integer lectureID;
    private Task task;

    private static Integer createCount = 0;

    public Homework(String name, Lecture lecture) {
        this.ID = createCount++;
        CreationDate = LocalDateTime.now();
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

    public Optional<Integer> getLectureID() {
        return Optional.ofNullable(lectureID);
    }

    public void setLectureID(Integer lectureID) {
        this.lectureID = lectureID;
    }

    public Optional<Task> getTask() {
        return Optional.ofNullable(task);
    }

    public void setTask(Task task) {
        this.task = task;
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

    @Override
    public String toString() {
        return "Homework{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lectureID=" + lectureID +
                ", courseName =" + course.getName() +
                ", deadline =" + formatDate(deadlineDate, DATE_FORMAT, locale) +
                ", task=" + task +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(ID, homework.ID) && Objects.equals(name, homework.name) && Objects.equals(lectureID, homework.lectureID) && Objects.equals(task, homework.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lectureID, task);
    }
}
