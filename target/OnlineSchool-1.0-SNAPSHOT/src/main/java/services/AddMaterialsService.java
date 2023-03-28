package services;

import models.Lecture;
import models.model_enum.ResourceType;
import models.AddMaterials;

public class AddMaterialsService {


    public AddMaterials create(String name, ResourceType resourceType, Lecture lecture) {
        return new AddMaterials(name, resourceType, lecture);
    }


}
