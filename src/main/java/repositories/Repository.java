package repositories;

import models.Model;

import java.util.List;
import java.util.Optional;

public interface Repository<E extends Model> {

    List<E> getRepository();

    Optional<E> getById(Integer id);

    List<E> sortedByName();
}
