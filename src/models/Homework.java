package models;

public class Homework {
    private Integer idHomeWork;
    private String nameHomework;
    public static Long CREATE_COUNT_HOMEWORK = 0L;

    public Homework() {
        CREATE_COUNT_HOMEWORK++;
    }
}
