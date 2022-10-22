package models;

public class Course {
    private final Integer idCourse;
    private String nameCourse;
    private Teacher teacher;
    private Student student;
    private Lecture lecture;
    private Homework homework;
    private AddTask addTask;
    public static Integer CREATE_COUNT_COURSE = 0;

    public Course() {
        CREATE_COUNT_COURSE++;
        this.idCourse = CREATE_COUNT_COURSE;
    }

    public Course(String nameCourse) {
        CREATE_COUNT_COURSE++;
        this.idCourse = CREATE_COUNT_COURSE;
        this.nameCourse = nameCourse;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }
}
