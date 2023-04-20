package utils;

import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new org.hibernate.cfg.Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }

    public static void shutdown () {
        getSessionFactory().close();
    }
}
