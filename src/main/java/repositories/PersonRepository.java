package repositories;


import models.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.log.Log;

import java.util.*;

import static java.util.stream.Collectors.toList;


public class PersonRepository implements Repository<Person> {

    private final String nameLog = "Log OnlineSchool";


    @Override
    public List<Person> getRepository() {
        List<Person> repository = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx;
            tx = session.beginTransaction();
            Query<Person> query = session.createQuery("FROM Person", Person.class);
            repository = query.list();
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error get repository in PersonRepository", e.getStackTrace());
        }
        return repository;
    }

    @Override
    public Optional<Person> getById(long id) {
        Person person = new Person();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx;
            tx = session.beginTransaction();
            person = session.get(Person.class, id);
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error get by id in PersonRepository", e.getStackTrace());
        }

        return Optional.of(person);
    }

    @Override
    public List<Person> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(toList());
    }

    public void savePersonToRepository(Person person) {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            tx = session.beginTransaction();
            session.persist(person);
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error save to Base in PersonRepository", e.getStackTrace());
        }
    }


}

