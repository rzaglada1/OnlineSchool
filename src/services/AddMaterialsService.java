package services;

import models.model_enum.ResourceType;
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

    public AddMaterials create(String name, ResourceType resourceType, Integer lectureID) {
        return new AddMaterials(name, resourceType, lectureID);
    }

}
