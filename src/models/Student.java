package models;

public class Student {
    private Integer idStudent;
    private String nameStudent;
    public static Long CREATE_COUNT_STUDENT = 0L;

    public Student() {
        CREATE_COUNT_STUDENT++;
    }
}
