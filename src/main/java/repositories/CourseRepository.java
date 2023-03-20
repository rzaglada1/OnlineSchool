package repositories;

import models.Course;
import utils.data_base_property.DataBaseProperty;
import utils.log.Log;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CourseRepository implements Repository<Course> {

    private static CourseRepository instance;

    Map<String, String> properties;
    String nameLog = "Log OnlineSchool";


    private CourseRepository() {
        properties = new DataBaseProperty().loadFromServiceFile();
    }

    public static CourseRepository getInstance() {
        if (instance == null) {
            instance = new CourseRepository();
        }
        return instance;
    }


    @Override
    public List<Course> getRepository() {
        String query = "SELECT * FROM  courses";
        return dataBase(query);
    }


    @Override
    public void printRepository() {
        String query = "SELECT * FROM  courses";
        dataBase(query).forEach(System.out::println);
    }

    @Override
    public Optional<Course> getById(Integer id) {
        String query = "SELECT * FROM  courses";
        return dataBase(query).stream().filter(element -> element.getID().equals(id)).findAny();
    }

    @Override
    public List<Course> sortedByName() {
        String query = "SELECT * FROM  courses";
        return dataBase(query).stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

    public void saveToRepository (Course course ) {
        String query = "INSERT INTO courses (name, create_date) VALUES ('" + course.getName() + "', now()) ";

        try (Connection connection = DriverManager
                .getConnection(properties.get("URL"), properties.get("USER"), properties.get("PASSWORD"));
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);

        } catch (
                SQLException e) {
            Log.warning(nameLog, "Error saveRepository in CourseRepository", e.getStackTrace());
        }

    }

    private List<Course> dataBase(String query) {
        List<Course> repository = new ArrayList<>();
        try (Connection connection = DriverManager
                .getConnection(properties.get("URL"), properties.get("USER"), properties.get("PASSWORD"));
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id") ;
                String name = resultSet.getString("name");
                LocalDateTime localDateTimeString = resultSet.getTimestamp("create_date").toLocalDateTime();
                repository.add(new Course(id, name, localDateTimeString) );
            }

        } catch (
                SQLException e) {
            Log.warning(nameLog, "Error getRepository in CourseRepository", e.getStackTrace());
        }
        return repository;
    }

}
