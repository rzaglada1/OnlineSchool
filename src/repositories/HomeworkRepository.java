package repositories;

import models.Model;

public class HomeworkRepository extends ModelRepository {

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
}
