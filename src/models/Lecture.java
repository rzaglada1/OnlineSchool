package models;

public class Lecture {
    private final Integer idLecture;
    private String nameLecture;
    //public Integer idCourse;
    public static Integer CREATE_COUNT_LECTURE = 0;

    public Lecture() {
        CREATE_COUNT_LECTURE++;
        this.idLecture = CREATE_COUNT_LECTURE;
    }

    public Lecture(String nameLecture) {
        CREATE_COUNT_LECTURE++;
        this.idLecture = CREATE_COUNT_LECTURE;
        this.nameLecture = nameLecture;
    }

    public Integer getIdLecture() {
        return idLecture;
    }

    public String getNameLecture() {
        return nameLecture;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "idLecture=" + idLecture +
                ", nameLecture='" + nameLecture + '\'' +
                '}';
    }
}
