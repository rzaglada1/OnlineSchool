package models;

public class Teacher {
    private Integer idTeacher;
    private String nameTeacher;
    public static Long CREATE_COUNT_TEACHER = 0L;

    public Teacher() {
        CREATE_COUNT_TEACHER++;
    }
}
