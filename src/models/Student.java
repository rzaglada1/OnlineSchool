package models;

public class Student {
    private final Integer idStudent;
    private String nameStudent;
    public static Integer CREATE_COUNT_STUDENT = 0;

    public Student() {
        CREATE_COUNT_STUDENT++;
        this.idStudent = CREATE_COUNT_STUDENT;
    }

    public Student(String nameStudent) {
        CREATE_COUNT_STUDENT++;
        this.idStudent = CREATE_COUNT_STUDENT;
        this.nameStudent = nameStudent;
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }
}
