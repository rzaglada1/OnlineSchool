package repositories;

import models.Course;

public class CourseRepository {
    private int capacity = 10;
    private int capacityOld = 0;
    private Course[] courseRepository = new Course[capacity];

    public void add(Course course) {
        //System.out.println(courseRepository.length);
        for (int i = 0; i < courseRepository.length; i++) {
            if (courseRepository[i] == null) {
                courseRepository[i] = course;
                //System.out.println("added in array");
                return;
            }
        }
        expandArray();
        System.out.println("expanding array to " + courseRepository.length);
        courseRepository[capacityOld] = course;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Course[] tmpCourseRepository = new Course[capacity];
        System.arraycopy(courseRepository, 0, tmpCourseRepository, 0, capacityOld);
        courseRepository = tmpCourseRepository;
    }

    public Course[] getCourseRepository() {
        return courseRepository;
    }


}
