package models;

public class Person extends Model {
    private static Integer CREATE_COUNT = 0;

    private int courseID;
    private Role role;


    public Person() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Person(String name, Role role, Model course) {
        this();
        this.role = role;
        this.courseID = course.getID();
        setName(name);
    }


    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    public int getCourseID() {
        return courseID;
    }

    public Role getRole() {
        return role;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
