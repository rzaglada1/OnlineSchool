package backup;

import models.Model;

import java.util.ArrayList;
import java.util.List;

public class ServiceBackupRepository<E extends Model> {

    private final List<E> repo = new ArrayList<>();

    public List<E> createRepo(List<E> list, int courseID) {
        return list.stream().filter(element -> element.getCourse().isPresent()
                && element.getCourse().get().getID() == courseID).toList();
    }

}
