package models;

public class Course extends Model {

    private Person person;
    private Lecture lecture;
    private Homework homework;
    private AddTask addTask;
    private static Integer CREATE_COUNT = 0;

    public Course() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public Course(String name) {
        this();
        setName(name);
    }

    public Course(String name, Person person, Lecture lecture) {
        this(name);
        this.person = person;
        this.lecture = lecture;
    }

    public Person getPerson() {
        return person;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public Homework getHomework() {
        return homework;
    }

    public AddTask getAddTask() {
        return addTask;
    }

    public static Integer getCreateCount() {
        return CREATE_COUNT;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public void setAddTask(AddTask addTask) {
        this.addTask = addTask;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + getID() +
                ", nameCourse='" + getName() + '\'' +
                ", person=" + person +
                ", lecture=" + lecture +
                ", homework=" + homework +
                ", addTask=" + addTask +
                '}';
    }
}
