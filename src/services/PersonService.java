package services;

import models.Model;
import models.Person;
import models.Role;
import repositories.PersonRepository;

public class PersonService {

    public Person createPerson() {
        return new Person();
    }

    public Person createPerson(String name, Role role, Model course) {
        return new Person(name, role, course);
    }


    public void printObjectsRepository(PersonRepository personRepository) {

        Model[] personArray = personRepository.getAll();

        for (Model person : personArray) {
            if (person != null) {
                System.out.println("Name person - \"" + person.getName() + "\". person id = " + person.getID());
            }


        }
    }


}
