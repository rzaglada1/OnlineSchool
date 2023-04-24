package repositories;


import models.Homework;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.log.Log;

import java.util.*;
import java.util.stream.Collectors;

public class HomeWorkRepository implements Repository<Homework> {

    private final String nameLog = "Log OnlineSchool";

    @Override
    public List<Homework> getRepository() {
        List<Homework> repository = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx;
            tx = session.beginTransaction();
            Query<Homework> query = session.createQuery("FROM Homework", Homework.class);
            repository = query.list();
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error get repository in HomeworkRepository", e.getStackTrace());
        }
        return repository;
    }


    @Override
    public Optional<Homework> getById(long id) {
        Homework homework = new Homework();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx;
            tx = session.beginTransaction();
            homework = session.get(Homework.class, id);
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error get by id in HomeworkRepository", e.getStackTrace());
        }

        return Optional.of(homework);
    }

    @Override
    public List<Homework> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(Homework::getName))
                .collect(Collectors.toList());
    }

    public void saveHomeworkToRepository(Homework homework) {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            tx = session.beginTransaction();
            session.persist(homework);
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error save to Base in HomeworkRepository", e.getStackTrace());
        }
    }

}
