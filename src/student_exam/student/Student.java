package student_exam.student;

import student_exam.repo.StudentRepo;

import java.util.concurrent.Phaser;

public class Student implements Runnable {
    private final String name;
    private final int examTask;
    private final int examTime;
    private final Phaser phaser;


    public Student(String name, int examTask, int examTime, Phaser phaser) {
        this.name = name;
        this.examTask = examTask;
        this.examTime = examTime;
        this.phaser = phaser;
    }

    public String getName() {
        return name;
    }

    public int getExamTime() {
        return examTime;
    }

    @Override
    public void run() {
        try {
            phaser.arriveAndAwaitAdvance();
            System.out.println("Student " + name + " started. Work time " + examTime);
            Thread.sleep(examTime * 1000L);
            synchronized (StudentRepo.class) {
                StudentRepo.getInstance().getBestStudent().add(this);
            }
        } catch (InterruptedException e) {
            System.out.println("Error student  " + this.getName());
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", examTask=" + examTask +
                ", examTime=" + examTime +
                '}' + '\n';
    }
}
