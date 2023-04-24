package models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "homework", schema = "online_school")
public class Homework implements Model, Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "create_date")
    private final LocalDateTime CreationDate;



    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Transient
    private long lectureID;
    @Transient
    private final String DATE_FORMAT = "MMM d,  HH:mm";
    @Transient
    private final Locale locale = Locale.US;
    @Transient
    private LocalDateTime deadlineDate;

    public Homework(){
        CreationDate = LocalDateTime.now();
    }

    public Homework(String name, Lecture lecture) {
        this();
        this.name = name;
        this.lectureID = lecture.getID();
        this.lecture = lecture;
        this.deadlineDate = lecture.getLectureDate().plusDays(1).withHour(12).withMinute(0);
    }


    public void setID(long ID) {
        this.ID = ID;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Optional<Long> getLectureID() {
        return Optional.ofNullable(lectureID);
    }

    public void setLectureID(long lectureID) {
        this.lectureID = lectureID;
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
        return ID == homework.ID && lectureID == homework.lectureID && Objects.equals(name, homework.name) && Objects.equals(CreationDate, homework.CreationDate) && Objects.equals(lecture, homework.lecture) && Objects.equals(DATE_FORMAT, homework.DATE_FORMAT) && Objects.equals(locale, homework.locale) && Objects.equals(deadlineDate, homework.deadlineDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, CreationDate, lecture, lectureID, DATE_FORMAT, locale, deadlineDate);
    }
}
