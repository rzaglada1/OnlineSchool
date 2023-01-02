package models;

import models.model_enum.ResourceType;

import java.util.Objects;

public class AddMaterials extends Model {

    private Integer ID;
    private String name;
    private static Integer CREATE_COUNT = 0;

    private Integer lectureId;
    private ResourceType resourceType;


    public AddMaterials() {
        CREATE_COUNT++;
        setID(CREATE_COUNT);
    }

    public AddMaterials(String name) {
        this();
        this.name = name;
    }

    public AddMaterials(String name, ResourceType resourceType) {
        this();
        this.name = name;
        this.resourceType = resourceType;
    }

    public AddMaterials(String name, ResourceType resourceType, Integer lectureId) {
        this();
        this.name = name;
        this.resourceType = resourceType;
        this.lectureId = lectureId;
    }


    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "AddMaterials{" +
                "name =" + getName() +
                ", addMaterialsId=" + getID() +
                ", ResourceType =" + resourceType +
                ", lectureID=" + lectureId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMaterials that = (AddMaterials) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(lectureId, that.lectureId) && resourceType == that.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lectureId, resourceType);
    }
}
