package models;

public class Lecture {
    private final Integer idLecture;
    private Integer idCourse;
    private String nameLecture;

    private static Integer CREATE_COUNT_LECTURE = 0;

    public Lecture() {
        CREATE_COUNT_LECTURE++;
        this.idLecture = CREATE_COUNT_LECTURE;
    }

    public Lecture(String nameLecture) {
        CREATE_COUNT_LECTURE++;
        this.idLecture = CREATE_COUNT_LECTURE;
        this.nameLecture = nameLecture;
    }

    public Lecture(String nameLecture, int idCourse) {
        CREATE_COUNT_LECTURE++;
        this.idLecture = CREATE_COUNT_LECTURE;
        this.nameLecture = nameLecture;
        this.idCourse = idCourse;
    }

    public Integer getIdLecture() {
        return idLecture;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public String getNameLecture() {
        return nameLecture;
    }


    public static Integer getCreateCountLecture() {
        return CREATE_COUNT_LECTURE;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "idLecture=" + idLecture +
                ", nameLecture='" + nameLecture + '\'' +
                '}';
    }
}
