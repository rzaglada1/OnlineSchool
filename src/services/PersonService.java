package services;

import models.Person;
import models.model_enum.Role;

public class PersonService {

    public Person create() {
        return new Person();
    }

    public Person create(String[] personAttribute, Role role) {
        return new Person(personAttribute, role);
    }


}
