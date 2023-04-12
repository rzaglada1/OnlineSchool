package models;

import models.model_enum.ResourceType;
import services.LectureService;
import utils.log.Log;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class AddMaterials implements Model, Serializable {

    private  LectureService lectureService;



    public AddMaterials() {
    }

    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    private  Integer ID;
    private String name;
    private static Integer createCount = 0;
    private  LocalDateTime CreationDate;


    private Lecture lecture;
    private Course course;
    private  Integer lectureID;
    private ResourceType resourceType;


    public AddMaterials(String name, ResourceType resourceType, Lecture lecture) {
        this.ID = createCount++;
        CreationDate = LocalDateTime.now();
        this.name = name;
        this.resourceType = resourceType;
        this.lecture = lecture;
        lectureID = lecture.getID();
        try {
            course = lecture.getCourse().orElseThrow(NullPointerException::new);
        }catch (NullPointerException e){
            Log.error("Model AddMaterials", "NullPointerException  course", e.getStackTrace());
        }
    }


    private Optional<Course> getCourse(int lectureID) {
        return lectureService
                .getLectureById(lectureID).orElseThrow(NoSuchElementException::new).getCourse();
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Optional<Integer> getLectureID() {
        return Optional.ofNullable(lectureID);
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Optional<Course> getCourse() {
        return Optional.ofNullable(course);
    }


    @Override
    public String toString() {
        return "AddMaterials{" +
                "name =" + getName() +
                ", addMaterialsId=" + getID() +
                ", ResourceType =" + resourceType +
                ", lectureID=" + lectureID +
                ", courseName=" + course.getName() +
                '}' + '\n';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMaterials that = (AddMaterials) o;
        return Objects.equals(ID, that.ID) && Objects.equals(name, that.name) && Objects.equals(CreationDate, that.CreationDate) && Objects.equals(lecture, that.lecture) && Objects.equals(course, that.course) && Objects.equals(lectureID, that.lectureID) && resourceType == that.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, CreationDate, lecture, course, lectureID, resourceType);
    }
}
