package models;

public class Lecture {
    private final Integer id;
    private Integer idCourse;
    private String name;

    private static Integer CREATE_COUNT = 0;

    public Lecture() {
        CREATE_COUNT ++;
        this.id = CREATE_COUNT;
    }

    public Lecture(String name) {
        CREATE_COUNT++;
        this.id = CREATE_COUNT;
        this.name = name;
    }

    public Lecture(String name, int idCourse) {
        CREATE_COUNT++;
        this.id = CREATE_COUNT;
        this.name = name;
        this.idCourse = idCourse;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public String getName() {
        return name;
    }


    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "idLecture=" + id +
                ", nameLecture='" + name + '\'' +
                '}';
    }
}
