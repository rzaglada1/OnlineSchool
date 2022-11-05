package models;

public class Homework {
    private final Integer idHomeWork;
    private String nameHomework;
    private static Integer CREATE_COUNT_HOMEWORK = 0;

    public Homework() {
        CREATE_COUNT_HOMEWORK++;
        this.idHomeWork = CREATE_COUNT_HOMEWORK;
    }

    public Homework(String nameHomework) {
        CREATE_COUNT_HOMEWORK++;
        this.idHomeWork = CREATE_COUNT_HOMEWORK;
        this.nameHomework = nameHomework;
    }

    public Integer getIdHomeWork() {
        return idHomeWork;
    }

    public String getNameHomework() {
        return nameHomework;
    }

    public static Integer getCreateCountHomework() {
        return CREATE_COUNT_HOMEWORK;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "idHomeWork=" + idHomeWork +
                ", nameHomework='" + nameHomework + '\'' +
                '}';
    }
}
