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

    public Lecture createLecture(String name, Model course) {
        return new Lecture(name, course);
    }

    public void printObjectsRepository(LectureRepository lectureRepository) {

        Model[] lectureArray = lectureRepository.getAll();

        for (Model lecture : lectureArray) {
            if (lecture != null) {
                if (((Lecture) lecture).getPerson() != null) {
                    System.out.println(
                            "Name lecture - \"" + lecture.getName()
                                    + "\". Lecture id = " + lecture.getID()
                                    + "\". Person id = " + ((Lecture) lecture).getPersonID()
                                    + "\". Person Name = " + ((Lecture) lecture).getPerson().getName()
                                    + "\". Person Role = " + ((Lecture) lecture).getPerson().getRole()
                    );

                } else {
                    System.out.println(
                            "Name lecture - \"" + lecture.getName()
                                    + "\". Lecture id = " + lecture.getID()
                    );
                }
            }
        }
    }

}
