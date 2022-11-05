package repositories;

import models.Lecture;

public class LectureRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Lecture[] lectureRepository = new Lecture[capacity];

    public void add(Lecture lecture) {
        //System.out.println("lectureRepository.length = " + lectureRepository.length);
        for (int i = 0; i < lectureRepository.length; i++) {
            if (lectureRepository[i] == null) {
                lectureRepository[i] = lecture;
                //System.out.println("added in array");
                return;
            }
        }
        expandArray();
        System.out.println("expanded array to " + lectureRepository.length);
        lectureRepository[capacityOld] = lecture;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Lecture[] tmpLectureRepository = new Lecture[capacity];
        System.arraycopy(lectureRepository, 0, tmpLectureRepository, 0, capacityOld);
        lectureRepository = tmpLectureRepository;
    }

    public Lecture[] getLectureRepository() {
        return lectureRepository;
    }
}
