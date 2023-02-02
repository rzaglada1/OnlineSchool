package models;

import exceptions.EntityNotFoundException;
import models.model_enum.ResourceType;
import repositories.LectureRepository;
import utils.log.Log;

import java.time.LocalDateTime;
import java.util.Objects;

public class AddMaterials extends Model {

    private Integer ID;
    private String name;
    private static Integer createCount = 0;
    private final LocalDateTime CreationDate;


    private Lecture lecture;
    private Course course;
    private Integer lectureID;
    private ResourceType resourceType;


    private Course getCourse(int lectureID) throws EntityNotFoundException {
        return LectureRepository.getInstance().getById(lectureID).getCourse();
    }

    public AddMaterials(String name, ResourceType resourceType, Lecture lecture) {
        createCount++;
        setID(createCount);
        CreationDate = LocalDateTime.now();
        this.name = name;
        this.resourceType = resourceType;
        this.lecture = lecture;
        lectureID = lecture.getID();
        course = lecture.getCourse();
    }


    public Integer getLectureID() {
        return lectureID;
    }

    public void setLectureID(Integer lectureID) {
        this.lectureID = lectureID;
        try {
            this.course = getCourse(lectureID);
        } catch (EntityNotFoundException e) {
            Log.error("On-line school", "Error EntityNotFound ", e.getStackTrace());
        }

    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public LocalDateTime getCreationDate() {
        return CreationDate;
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
    public Model getCourse() {
        return course;
    }


    @Override
    public String toString() {
        return "AddMaterials{" +
                "name =" + getName() +
                ", addMaterialsId=" + getID() +
                ", ResourceType =" + resourceType +
                ", lectureID=" + lectureID +
                ", courseName=" + course.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMaterials that = (AddMaterials) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(lectureID, that.lectureID) && resourceType == that.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lectureID, resourceType);
    }
}
