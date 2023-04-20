package repositories;

import models.Course;
import models.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import utils.HibernateUtil;
import utils.log.Log;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class CourseRepository implements Repository<Course> {



//    private DriverManagerDataSource dataSource;
//
//    @Autowired
//    public void setDataSource(DriverManagerDataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private final String nameLog = "Log OnlineSchool";


//    @Override
//    public List<Course> getRepository() {
//        String query = "SELECT * FROM  courses";
//        List<Course> repository = new ArrayList<>();
//        try (
//               Connection connection = dataSource.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                long id = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                LocalDateTime localDateTimeString = resultSet.getTimestamp("create_date").toLocalDateTime();
//                repository.add(new Course(id, name, localDateTimeString));
//            }
//        } catch (
//                SQLException e) {
//            Log.warning(nameLog, "Error getRepository in CourseRepository", e.getStackTrace());
//        }
//        return repository;
//    }


//    @Override
//    public List<Course> getRepository() {
//        List<Course> repository = new ArrayList<>();
//        Transaction tx = null;
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()){
//
//
//            tx = session.beginTransaction();
//            System.out.println("111");
//            Query<Course> query = session.createQuery("FROM Course", Course.class);
//            System.out.println("22222");
//            repository = query.list();
//            System.out.println("333333");
//            tx.commit();
//            System.out.println("44444444");
//
//        } catch (Exception e) {
//            System.out.println("9999999999999");
//            e.printStackTrace();
//        }
//        return repository;
//    }


    @Override
    public List<Course> getRepository() {
        List<Test> repository = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();
            System.out.println("111");

            Query<Test> query = session.createQuery("FROM Test", Test.class);

//            NativeQuery<Test> query = session.createNativeQuery("SELECT * FROM tests");
//            query.addEntity(Test.class);

            System.out.println("22222");
            repository = query.list();
            repository.forEach(System.out::println);
            System.out.println("333333");
            tx.commit();
            System.out.println("44444444");

        } catch (Exception e) {
            System.out.println("9999998888889999999");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }






    @Override
    public Optional<Course> getById(long id) {
        String query = "SELECT * FROM  courses WHERE id=?";
        Course course = new Course();
//        try ( //Connection connection = dbConnection.getConnect();
//              Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                course.setID(resultSet.getInt("id"));
//                course.setName(resultSet.getString("name"));
//                course.setCreationDate(resultSet.getTimestamp("create_date").toLocalDateTime());
//            }
//            resultSet.close();
//
//        } catch (
//                SQLException e) {
//            Log.warning(nameLog, "Error getById in CourseRepository", e.getStackTrace());
//        }
        return Optional.of(course);
    }

    @Override
    public List<Course> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }


    public void saveCourseToRepository(Course course) {
        String query = "INSERT INTO courses (name, create_date) VALUES (?, now()) ";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, course.getName());
//            preparedStatement.executeUpdate();
//
//        } catch (
//                SQLException e) {
//            Log.warning(nameLog, "Error saveRepository in CourseRepository", e.getStackTrace());
//        }
    }


}
