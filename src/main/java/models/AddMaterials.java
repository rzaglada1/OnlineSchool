package models;

import jakarta.persistence.*;
import models.model_enum.ResourceType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "add_materials", schema = "online_school")
public class AddMaterials implements Model, Serializable {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "create_date")
    private LocalDateTime creationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "resource_type")
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Transient
    private long lectureID;

    public AddMaterials() {
        creationDate = LocalDateTime.now();
    }


    public AddMaterials(String name, ResourceType resourceType, Lecture lecture) {
        this();
        this.name = name;
        this.resourceType = resourceType;
        this.lecture = lecture;
        lectureID = lecture.getID();
    }


    public void setID(long ID) {
        this.ID = ID;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLectureID(long lectureID) {
        this.lectureID = lectureID;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Optional<Long> getLectureID() {
        long id = -1;
        if (lecture != null) {
            id = lecture.getID();
        }
        return Optional.of(id);
    }


    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public long getID() {
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


    @Override
    public String toString() {
        return "AddMaterials{" +
                "name =" + getName() +
                ", addMaterialsId=" + getID() +
                ", ResourceType =" + resourceType +
                '}' + '\n';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMaterials that = (AddMaterials) o;
        return ID == that.ID && lectureID == that.lectureID && Objects.equals(name, that.name) && Objects.equals(creationDate, that.creationDate) && Objects.equals(lecture, that.lecture) && resourceType == that.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, creationDate, lecture, resourceType, lectureID);
    }
}
