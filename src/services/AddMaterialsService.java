package services;

import ModelEnum.ResourceType;
import models.AddMaterials;

public class AddMaterialsService {

    public AddMaterials create() {
        return new AddMaterials();
    }

    public AddMaterials create(String name) {
        return new AddMaterials(name);
    }

    public AddMaterials create(String name, ResourceType resourceType) {
        return new AddMaterials(name, resourceType);
    }

    public AddMaterials create(String name, ResourceType resourceType, Integer lectureId) {
        return new AddMaterials(name, resourceType, lectureId);
    }

}
