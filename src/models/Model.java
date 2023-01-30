package models;

import java.io.Serializable;

public abstract class Model implements Serializable {

    public abstract Integer getID() ;
    public abstract void setID(Integer ID) ;

    public abstract String getName() ;

    public abstract void setName(String name) ;

    public abstract Model getCourse() ;

}
