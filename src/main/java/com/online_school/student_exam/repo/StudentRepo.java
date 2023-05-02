package com.online_school.student_exam.repo;

import com.online_school.student_exam.student.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo {
    private static StudentRepo instance;
    private final List<Student> repoStudent;
    private final List<Student> bestStudent;

    private StudentRepo() {
        repoStudent = new ArrayList<>();
        bestStudent = new ArrayList<>();
    }

    public List<Student> getRepoStudent() {
        return repoStudent;
    }

    public List<Student> getBestStudent() {
        return bestStudent;
    }

    public static StudentRepo getInstance() {
        if (instance == null) {
            instance = new StudentRepo();
        }
        return instance;
    }

    public void printRepo () {
        System.out.println("The best students: ");
        for (int i = 0; i < bestStudent.size(); i++) {
            System.out.println(bestStudent.get(i).getName() + ", time " + bestStudent.get(i).getExamTime());
        }

        System.out.println("=======================" + '\n');
    }

}

