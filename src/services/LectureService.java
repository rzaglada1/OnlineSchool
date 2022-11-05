package services;


import models.Lecture;
import repositories.LectureRepository;


public class LectureService {

    public Lecture createLecture() {
        return new Lecture();
    }

    public Lecture createLecture(String nameLecture) {
        return new Lecture(nameLecture);
    }

    public Lecture createLecture(String nameLecture, int idCourse) {
        return new Lecture(nameLecture, idCourse);
    }

    public void printObjectsRepository(LectureRepository lectureRepository) {

        Lecture[] lectureArray = lectureRepository.getLectureRepository();

        for (Lecture lecture : lectureArray) {
            if (lecture == null) {
                break;
            }
            System.out.println("Name lecture - \"" + lecture.getNameLecture() + "\". Lecture id = " + lecture.getIdLecture());

        }
    }
}
