package models;

import java.util.Optional;

public interface Model  {

    Integer getID() ;

    String getName() ;

    void setName(String name) ;

    Optional<Course> getCourse() ;

}
