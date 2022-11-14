package models;

public class Model {
    private Integer ID = -1;
    private String name;

    public Integer getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
