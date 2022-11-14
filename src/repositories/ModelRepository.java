package repositories;

import models.Model;

public class ModelRepository {
    private int capacity = 5;
    private int capacityOld = 0;
    private Model[] repository = new Model[capacity];

    public void add(Model model) {
        for (int i = 0; i < repository.length; i++) {
            if (repository[i] == null) {
                repository[i] = model;
                return;
            }
        }
        expandArray();
        repository[capacityOld] = model;
    }

    private void expandArray() {
        capacityOld = capacity;
        capacity = (capacity * 3) / 2 + 1;
        Model[] tmpRepository = new Model[capacity];
        System.arraycopy(repository, 0, tmpRepository, 0, capacityOld);
        repository = tmpRepository;
    }

    private void set(int ID, Model model) {
        if (ID >= 0 && ID < getAll().length) {
            getAll()[ID] = model;
        }
    }

    public Model[] getAll() {
        return repository;
    }

    public Model getById(int ID) {

        for (Model model : getAll()) {
            if (model != null && model.getID() == ID) {
                return model;
            }
        }
        return null;
    }

    public void deleteById(int ID) {

        for (int i = 0; i < getAll().length; i++) {
            //System.out.println(model.getID() );
            if (getAll()[i] != null && getAll()[i].getID() == ID) {
                set(i, null);
                System.out.println("Object with ID = " + ID + " - deleted");
                return;
            }
        }
        System.out.println("Object with ID = " + ID + " not found");

    }

    public int objectsTotal() {
        int i = 0;
        for (Model model : getAll()) {
            if (model != null) {
                i++;
            }
        }
        return i;
    }


}
