package repositories;

import models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonRepositoryTest {

//    static Person person0;
//    static Person person1;
//
//    @BeforeAll
//    static void createTestRepository() {
//        PersonRepository personRepository = PersonRepository.getInstance();
//
//        person0 = Mockito.mock(Person.class);
//        person1 = Mockito.mock(Person.class);
//
//
//        when(person0.getEmail()).thenReturn("someEmail0@google.com");
//        when(person1.getEmail()).thenReturn("someEmail1@google.com");
//
//        personRepository.getRepository().add(person0);
//        personRepository.getRepository().add(person1);
//    }
//
//    @Test
//    void TestCheckDoubleEmail() {
//        String emailTrue = "someEmail1@google.com";
//        String emailFalse = "anotherEmail@google.com";
//        boolean actualEmailTrue = PersonRepository.getInstance().checkDoubleEmail(emailTrue);
//        boolean actualEmailFalse = PersonRepository.getInstance().checkDoubleEmail(emailFalse);
//        assertTrue(actualEmailTrue);
//        assertFalse(actualEmailFalse);
//    }
}