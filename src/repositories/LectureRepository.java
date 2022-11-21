package repositories;

import models.Lecture;
import models.Model;
import models.Person;

public class LectureRepository extends ModelRepository {

    @Override
    public void add(Model model) {
        super.add(model);
    }

    @Override
    public Model[] getAll() {
        return super.getAll();
    }

    @Override
    public Lecture getById(int ID) {
        for (Model model : getAll()) {
            if (model != null && model.getID() == ID) {
                return (Lecture) model;
            }
        }
        System.out.println("Object with ID = " + ID + " not found");
        return null;
    }

    @Override
    public void deleteById(int ID) {
        super.deleteById(ID);
    }

    // set Teacher in Lecture
    public void setTeacher(Model lecture, Person person) {

        for (int i = 0; i < getAll().length; i++) {

            if (getAll()[i] != null && getAll()[i].getID().equals(lecture.getID())) {
                ((Lecture) getAll()[i]).setPerson(person);
            }
        }
    }


}
