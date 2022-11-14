package models;

public class Course extends Model {
    private Teacher teacher;
    private Student student;
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

    public Course(String name, Teacher teacher, Student student, Lecture lecture) {
        this(name);
        this.teacher = teacher;
        this.student = student;
        this.lecture = lecture;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getStudent() {
        return student;
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

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudent(Student student) {
        this.student = student;
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
                ", teacher=" + teacher +
                ", student=" + student +
                ", lecture=" + lecture +
                ", homework=" + homework +
                ", addTask=" + addTask +
                '}';
    }
}
