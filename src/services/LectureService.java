package services;


import models.Lecture;
import repositories.LectureRepository;


public class LectureService {

    public Lecture createLecture() {
        return new Lecture();
    }

    public Lecture createLecture(String name) {
        return new Lecture(name);
    }

    public Lecture createLecture(String name, int idCourse) {
        return new Lecture(name, idCourse);
    }

    public void printObjectsRepository(LectureRepository lectureRepository) {

        Lecture[] lectureArray = lectureRepository.getRepository();

        for (Lecture lecture : lectureArray) {
            if (lecture == null) {
                break;
            }
            System.out.println("Name lecture - \"" + lecture.getName () + "\". Lecture id = " + lecture.getId () );

        }
    }
}
