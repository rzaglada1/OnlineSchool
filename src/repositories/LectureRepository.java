package repositories;

import models.Lecture;

public class LectureRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Lecture[] repository = new Lecture[capacity];

    public void add(Lecture lecture) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = lecture;
                return;
            }
        }
        expandArray();
        repository[capacityOld] = lecture;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Lecture[] tmpRepository = new Lecture[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    public Lecture[] getRepository() {
        return repository;
    }
}
