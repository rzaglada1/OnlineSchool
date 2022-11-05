package models;

public class Teacher {
    private final Integer idTeacher;
    private String nameTeacher;
    private static Integer CREATE_COUNT_TEACHER = 0;

    public Teacher() {
        CREATE_COUNT_TEACHER++;
        this.idTeacher = CREATE_COUNT_TEACHER;
    }

    public Teacher(String nameTeacher) {
        CREATE_COUNT_TEACHER++;
        this.idTeacher = CREATE_COUNT_TEACHER;
        this.nameTeacher = nameTeacher;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public static Integer getCreateCountTeacher() {
        return CREATE_COUNT_TEACHER;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idTeacher=" + idTeacher +
                ", nameTeacher='" + nameTeacher + '\'' +
                '}';
    }
}
