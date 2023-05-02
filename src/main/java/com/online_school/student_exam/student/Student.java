package com.online_school.student_exam.student;

import com.online_school.student_exam.repo.StudentRepo;


public class Student implements Runnable {
    private final String name;
    private final int examTask;
    private final int examTime;

    public Student(String name, int examTask, int examTime) {
        this.name = name;
        this.examTask = examTask;
        this.examTime = examTime;
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
            System.out.println("Student " + name + " started. Work time " + examTime);
            Thread.sleep(examTime * 1000L);
            synchronized (StudentRepo.class) {
                StudentRepo.getInstance().getBestStudent().add(this);
            }
        } catch (InterruptedException e) {
            System.out.println("Time is up:  " + this.getName());
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
