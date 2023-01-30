package backup;

import models.Model;

import java.util.ArrayList;
import java.util.List;

public class ServiceBackupRepository<E extends Model> {

    private final List<E> repo = new ArrayList<>();

    public List<E> createRepo(List<E> list, int courseID) {
        for (E element : list) {

            if (element.getCourse().getID() == courseID) {
                repo.add(element);
            }
        }
        return repo;
    }

}
