package utils.data_base_property;

import utils.log.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataBaseProperty {
    String nameLog = "Log OnlineSchool";

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URI resourceURI;
    Path path;

    {
        try {
            resourceURI = Objects.requireNonNull(classLoader.getResource("/application.properties")).toURI();
            path = Paths.get(resourceURI);
        } catch (NullPointerException | URISyntaxException e) {
            path = Paths.get("./src/main/resources/application.properties");
//            Log.error(nameLog, "Error get URI application.properties in MenuUtils", e.getStackTrace());
        }
    }


    public Map<String, String> loadFromServiceFile() {
        Map<String, String> properties = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String row = reader.readLine();

            while (row != null && row.trim().length() != 0) {
                String key = row.substring(0, row.indexOf("=")).trim();
                String value = row.substring(row.indexOf("=") + 1).trim();
                properties.put(key, value);
                row = reader.readLine();
            }

        } catch (IOException e) {
            Log.error(nameLog, "Error in loadFromServiceFile from DataBaseProperty.class", e.getStackTrace());
        }
        return properties;
    }
}
