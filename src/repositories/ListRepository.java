package repositories;

import exceptions.EntityNotFoundException;
import models.Model;

import java.util.ArrayList;
import java.util.List;

public class ListRepository<E extends Model> {

    List<E> repository = new ArrayList<>();

    public List<E> getRepository() {
        return repository;
    }

    public E getById(int id) throws EntityNotFoundException {
        for (E element : repository) {
            if (element.getID() == id) {
                return element;
            }
        }
        throw new EntityNotFoundException("id object not found");
    }
}
