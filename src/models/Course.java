package models;

public class Course {
    private Integer idCourse;
    private String nameCourse;
    private Teacher teacher;
    private Student student;
    private Lecture lecture;
    private Homework homework;
    private AddTask addTask;
    public static Long CREATE_COUNT_COURSE = 0L;

    public Course() {
        CREATE_COUNT_COURSE++;
    }

    public Course(Integer idCourse, String nameCourse) {
        CREATE_COUNT_COURSE++;
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }
}
