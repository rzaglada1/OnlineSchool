package repositories;

import models.Course;

public class CourseRepository {
    private int capacity = 10;
    private int capacityOld = 0;
    private Course[] repository = new Course[capacity];

    public void add(Course course) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = course;
                return;
            }
        }
        expandArray();
        repository[capacityOld] = course;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Course[] tmpRepository = new Course[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    public Course[] getRepository() {
        return repository;
    }


}
