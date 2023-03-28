package utils.data_base_property;

import utils.MenuUtils;
import utils.log.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DataBaseProperty {
    String nameLog = "Log OnlineSchool";


    public Map<String, String> loadFromServiceFile() {
        Map<String, String> properties = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(MenuUtils.STR_PATH_DATA_BASE_PROPERTY))) {
            String row = reader.readLine();

            while (row != null && row.trim().length() !=0) {
                String key = row.substring(0, row.indexOf("=") ).trim();
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
