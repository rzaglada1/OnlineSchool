package utils.data_base;

import utils.data_base_property.DataBaseEnum;
import utils.data_base_property.DataBaseProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DbConnection {
    private static DbConnection instance;
    Map<String, String> properties;

    private DbConnection() {
        properties = new DataBaseProperty().loadFromServiceFile();
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }


    public Connection getConnect() throws SQLException {
        return DriverManager.getConnection(
                properties.get(DataBaseEnum.URL.name())
                , properties.get(DataBaseEnum.USER.name())
                , properties.get(DataBaseEnum.PASSWORD.name()));
    }


}
