package models;

public class Lecture extends Model {

    private static Integer CREATE_COUNT = 0;
    private int idCourse;
    private int personID;
    private Person person;

    public Lecture() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Lecture(String name) {
        this();
        setName(name);
    }

    public Lecture(String name, String description) {
        this(name);
        setDescription(description);
    }

    public Lecture(String name, Model course) {
        this(name);
        this.idCourse = course.getID();
    }


    public Lecture(String name, Model course, Model person) {
        this(name, course);
        this.idCourse = course.getID();
        this.personID = person.getID();
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


    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.personID = person.getID();
    }

    @Override
    public String toString() {
        if (person != null) {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    "idLecture=" + getID() +
                    ", personID=" + personID +
                    ", personFirstName=" + person.getFirstName() +
                    ", personLastName=" + person.getLastName() +
                    ", personRole=" + person.getRole() +
                    ", idCourse=" + idCourse +
                    '}';
        } else {
            return "Lecture{" +
                    "LectureName=" + getName() +
                    "idLecture=" + getID() +
                    ", idCourse=" + idCourse +
                    '}';
        }
    }


}
