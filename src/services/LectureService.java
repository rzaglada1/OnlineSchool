package services;


import models.Lecture;
import models.Model;
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

        Model[] lectureArray = lectureRepository.getAll();

        for (Model lecture : lectureArray) {
            if (lecture != null) {
                System.out.println("Name lecture - \"" + lecture.getName() + "\". Lecture id = " + lecture.getID());
            }


        }
    }
}
