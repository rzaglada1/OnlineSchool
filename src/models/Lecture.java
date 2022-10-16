package models;

public class Lecture {
    private Integer idLecture;
    private String nameLecture;
    public static Long CREATE_COUNT_LECTURE = 0L;

    public Lecture() {
        CREATE_COUNT_LECTURE++;
    }

}
