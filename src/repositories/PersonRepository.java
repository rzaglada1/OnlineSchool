package repositories;

import models.Model;
import models.Person;
import models.Role;

public class PersonRepository extends ModelRepository {
    @Override
    public void add(Model model) {
        super.add(model);
    }

    @Override
    public Model[] getAll() {

        return super.getAll();
    }

    @Override
    public Model getById(int ID) {
        return super.getById(ID);
    }

    @Override
    public void deleteById(int ID) {
        super.deleteById(ID);
    }

    @Override
    public int objectsTotal() {
        return super.objectsTotal();
    }

    // counter Student
    public int objectsTotalStudent() {
        int i = 0;
        for (Model model : (getAll())) {
            if (model != null && ((Person) model).getRole() == Role.STUDENT) {
                i++;
            }
        }
        return i;
    }

    // counter Teacher
    public int objectsTotalTeacher() {
        int i = 0;
        for (Model model : (getAll())) {
            if (model != null && ((Person) model).getRole() == Role.TEACHER) {
                i++;
            }
        }
        return i;
    }


}
