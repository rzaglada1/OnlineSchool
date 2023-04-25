package repositories;

import models.AddMaterials;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;
import utils.log.Log;

import java.util.*;
import java.util.stream.Collectors;

public class AddMaterialsRepository implements Repository<AddMaterials> {

    private final String nameLog = "Log OnlineSchool";


    @Override
    public List<AddMaterials> getRepository() {
        List<AddMaterials> repository = new ArrayList<>();
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query<AddMaterials> query = session.createQuery("FROM AddMaterials", AddMaterials.class);
            repository = query.list();
            tx.commit();
        } catch (Exception e) {
            Log.error(nameLog, "Error get repository in AddMaterialsRepository", e.getStackTrace());
        }
        return repository;
    }


    @Override
    public Optional<AddMaterials> getById(long id) {
        AddMaterials addMaterials = new AddMaterials();
        Transaction tx;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            addMaterials = session.get(AddMaterials.class, id);
            tx.commit();
        } catch (Exception e) {
            Log.error(nameLog, "Error get by id in AddMaterialsRepository", e.getStackTrace());
        }
        return Optional.of(addMaterials);
    }

    @Override
    public List<AddMaterials> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(AddMaterials::getName))
                .collect(Collectors.toList());
    }


    public void saveAddMaterialsToRepository(AddMaterials addMaterials) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(addMaterials);
            tx.commit();
        } catch (Exception e) {
            Log.error(nameLog, "Error save to Base in AddMaterialsRepository", e.getStackTrace());
            if (tx != null) {
                tx.rollback();
            }
        }
    }

}
