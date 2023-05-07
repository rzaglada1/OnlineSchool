package com.online_school.controllers.backup;

import com.online_school.models.Model;

import java.util.ArrayList;
import java.util.List;

public class ServiceBackupRepository<E extends Model> {

    private final List<E> repo = new ArrayList<>();

    public List<E> createRepo(List<E> list, long courseID) {
//        return list.stream().filter(element -> element.getCourse().isPresent()
//                && element.getCourse().get().getID() == courseID).toList();
        return null;
    }

}
