package services;

import models.Model;
import models.Person;
import models.Role;

public class PersonService {

    public Person create() {
        return new Person();
    }

    public Person create(String[] personAttribute, Role role, Model course) {
        return new Person(personAttribute, role, course);
    }


}
