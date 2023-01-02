package repositories;

import models.Model;

import java.util.HashMap;
import java.util.Map;

public class MapRepository <K,V extends Model> {
    Map <K, V> repository = new HashMap<>();

    public Map<K, V> getRepository() {
        return repository;
    }
}
