package repositories;


import models.Teacher;

public class TeacherRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Teacher[] teacherRepository = new Teacher[capacity];

    public void add(Teacher teacher) {
        //System.out.println("teacherRepository.length = " + teacherRepository.length);
        for (int i = 0; i < teacherRepository.length; i++) {
            if (teacherRepository[i] == null) {
                teacherRepository[i] = teacher;
                //System.out.println("added in array");
                return;
            }
        }
        expandArray();
        System.out.println("expanded array to " + teacherRepository.length);
        teacherRepository[capacityOld] = teacher;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Teacher[] tmpTeacherRepository = new Teacher[capacity];
        System.arraycopy(teacherRepository, 0, tmpTeacherRepository, 0, capacityOld);
        teacherRepository = tmpTeacherRepository;
    }

    public Teacher[] getTeacherRepository() {
        return teacherRepository;
    }
}
