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
    private final String nameLog = "Log OnlineSchool";


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
        List<Course> repository = new ArrayList<>();
        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime localDateTimeString = resultSet.getTimestamp("create_date").toLocalDateTime();
                repository.add(new Course(id, name, localDateTimeString));
            }
        } catch (
                SQLException e) {
            Log.warning(nameLog, "Error getRepository in CourseRepository", e.getStackTrace());
        }
        return repository;
    }


    public List<Course> getRepositoryByTableName(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        List<Course> repository = new ArrayList<>();
        try (Connection connection = getConnect();
             CallableStatement callableStatement = connection.prepareCall(sql);
             ResultSet resultSet = callableStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime localDateTimeString = resultSet.getTimestamp("create_date").toLocalDateTime();
                repository.add(new Course(id, name, localDateTimeString));
            }
        } catch (
                SQLException e) {
            Log.warning(nameLog, "Error getRepository in CourseRepository", e.getStackTrace());
        }
        return repository;
    }


    @Override
    public void printRepository() {
        getRepository().forEach(System.out::println);
    }

    @Override
    public Optional<Course> getById(Integer id) {
        String query = "SELECT * FROM  courses WHERE id=?";
        Course course = new Course();
        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                course.setID(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setCreationDate(resultSet.getTimestamp("create_date").toLocalDateTime());
            }
            resultSet.close();

        } catch (
                SQLException e) {
            Log.warning(nameLog, "Error getById in CourseRepository", e.getStackTrace());
        }
        return Optional.of(course);
    }

    @Override
    public List<Course> sortedByName() {
        return getRepository().stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }


    public void saveToRepository(Course course) {
        String query = "INSERT INTO courses (name, create_date) VALUES (?, now()) ";

        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.executeUpdate();

        } catch (
                SQLException e) {
            Log.warning(nameLog, "Error saveRepository in CourseRepository", e.getStackTrace());
        }
    }

    private Connection getConnect() throws SQLException {
        return DriverManager.getConnection(
                properties.get("URL")
                , properties.get("USER")
                , properties.get("PASSWORD"));
    }

}
