package models;

import java.io.Serializable;
import java.util.Optional;

public interface Model  {

    public  Integer getID() ;

    public String getName() ;

    public void setName(String name) ;

    public Optional<Course> getCourse() ;

}
