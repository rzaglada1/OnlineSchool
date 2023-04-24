package repositories;


import models.Lecture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.log.Log;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class LectureRepository implements Repository<Lecture>, Serializable {

    private final String nameLog = "Log OnlineSchool";

    @Override
    public List<Lecture> getRepository() {
        List<Lecture> repository = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx;
            tx = session.beginTransaction();
            Query<Lecture> query = session.createQuery("FROM Lecture", Lecture.class);
            repository = query.list();
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error get repository in LectureRepository", e.getStackTrace());
        }
        return repository;
    }


    @Override
    public Optional<Lecture> getById(long id) {
        Lecture lecture = new Lecture();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx;
            tx = session.beginTransaction();
            lecture = session.get(Lecture.class, id);
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error get by id in LectureRepository", e.getStackTrace());
        }

        return Optional.of(lecture);
    }

    @Override
    public List<Lecture> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(Lecture::getName))
                .collect(Collectors.toList());
    }


    public void saveLectureToRepository(Lecture lecture) {
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            tx = session.beginTransaction();
            session.persist(lecture);
            tx.commit();

        } catch (Exception e) {
            Log.error(nameLog, "Error save to Base in LectureRepository", e.getStackTrace());
        }
    }

}
