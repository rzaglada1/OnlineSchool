package repositories;

import models.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.log.Log;

import java.util.*;
import java.util.stream.Collectors;


public class CourseRepository implements Repository<Course> {

    private final String nameLog = "Log OnlineSchool";


    @Override
    public List<Course> getRepository() {
        List<Course> repository = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx;
            tx = session.beginTransaction();
            Query<Course> query = session.createQuery("FROM Course", Course.class);
            repository = query.list();
            tx.commit();
        } catch (Exception e) {
            Log.error(nameLog, "Error get repository in CourseRepository", e.getStackTrace());
        }
        return repository;
    }

    @Override
    public Optional<Course> getById(long id) {
        Course course = new Course();
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            course = session.get(Course.class, id);
            tx.commit();
        } catch (Exception e) {
            Log.error(nameLog, "Error get by id in CourseRepository", e.getStackTrace());
        }

        return Optional.of(course);
    }

    @Override
    public List<Course> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }


    public void saveCourseToRepository(Course course) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(course);
            tx.commit();
        } catch (Exception e) {
            Log.error(nameLog, "Error save to Base in CourseRepository", e.getStackTrace());
            if (tx != null) {
                tx.rollback();
            }
        }
    }


}
