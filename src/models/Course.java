package models;

public class Course {
    private final Integer id;
    private String name;
    private Teacher teacher;
    private Student student;
    private Lecture lecture;
    private Homework homework;
    private AddTask addTask;
    private static Integer CREATE_COUNT = 0;

    public Course() {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
    }

    public Course(String name) {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
        this.name = name;
    }

    public Course(String name, Teacher teacher, Student student, Lecture lecture) {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        this.lecture = lecture;
    }

    public Integer getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public static Integer getCreateCount () {
        return CREATE_COUNT;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + id +
                ", nameCourse='" + name + '\'' +
                ", teacher=" + teacher +
                ", student=" + student +
                ", lecture=" + lecture +
                ", homework=" + homework +
                ", addTask=" + addTask +
                '}';
    }
}
