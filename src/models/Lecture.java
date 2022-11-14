package models;

public class Lecture extends Model {

    private static Integer CREATE_COUNT = 0;
    private Integer idCourse;

    public Lecture() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Lecture(String name) {
        this();
        setName(name);
    }

    public Lecture(String name, int idCourse) {
        this(name);
        setIdCourse(idCourse);
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public String toString() {
        return "Object lecture{" +
                "id = " + getID() +
                ", name = '" + getName() + '\'' +
                '}';
    }
}
