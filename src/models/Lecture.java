package models;

public class Lecture {
    private Integer idLecture;
    private String nameLecture;
    public Integer idCourse;
    public static Long CREATE_COUNT_LECTURE = 0L;
    private Course course;

    public Lecture() {
        CREATE_COUNT_LECTURE++;
    }

    public Lecture(Integer idLecture, String nameLecture, Course course) {
        CREATE_COUNT_LECTURE++;
        this.idLecture = idLecture;
        this.nameLecture = nameLecture;
        this.idCourse = course.getIdCourse();
    }

    public Integer getIdLecture() {
        return idLecture;
    }

    public String getNameLecture() {
        return nameLecture;
    }

    public Integer getIdCourse() {
        return idCourse;
    }
}
