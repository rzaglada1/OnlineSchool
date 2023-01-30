package services;

import models.Model;
import models.model_enum.ResourceType;
import models.AddMaterials;

public class AddMaterialsService {

    public AddMaterials create() {
        return new AddMaterials();
    }

    public AddMaterials create(String name) {
        return new AddMaterials(name);
    }

    public AddMaterials create(String name, Model course) {
        return new AddMaterials(name, course);
    }


    public AddMaterials create(String name, ResourceType resourceType) {
        return new AddMaterials(name, resourceType);
    }

    public AddMaterials create(String name, ResourceType resourceType, Integer lectureID) {
        return new AddMaterials(name, resourceType, lectureID);
    }

    public AddMaterials create(String name, ResourceType resourceType, Integer lectureID, Model course) {
        return new AddMaterials(name, resourceType, lectureID, course);
    }


}
