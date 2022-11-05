package repositories;

import models.Homework;

public class HomeworkRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Homework[] homeworkRepository = new Homework[capacity];

    public void add(Homework homework) {
        //System.out.println("homeworkRepository.length = " + homeworkRepository.length);
        for (int i = 0; i < homeworkRepository.length; i++) {
            if (homeworkRepository[i] == null) {
                homeworkRepository[i] = homework;
                //System.out.println("added in array");
                return;
            }
        }
        expandArray();
        System.out.println("expanded array to " + homeworkRepository.length);
        homeworkRepository[capacityOld] = homework;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Homework[] tmpHomeworkRepository = new Homework[capacity];
        System.arraycopy(homeworkRepository, 0, tmpHomeworkRepository, 0, capacityOld);
        homeworkRepository = tmpHomeworkRepository;
    }

    public Homework[] getHomeworkRepository() {
        return homeworkRepository;
    }
}
