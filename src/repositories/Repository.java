package repositories;

import exceptions.EntityNotFoundException;
import models.Model;
import java.util.List;

public interface Repository <E extends Model> {
    void printRepository ();
    List<E> getRepository();

    E getById(Integer id) throws EntityNotFoundException;

    List<E> sortedByName();
}
