package utils.log;

import java.io.*;
import java.util.Arrays;


public class LogToFile {

    String path = "./src/utils/log/";
    String nameFile = "Log.txt";
    File file = new File(path, nameFile);
    String nameLog = "Log OnlineSchool";
    private static LogToFile instance;

    private LogToFile() {
        createFile();
    }

    public static LogToFile getInstance() {
        if (instance == null) {
            instance = new LogToFile();
        }
        return instance;
    }


    private void createFile() {

        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    Log.debug(nameLog, "File Log.txt not created");
                }
            } catch (IOException e) {
                Log.error(nameFile, "Error created file Log.txt", e.getStackTrace());
            }
        }
    }


    public boolean saveToFile(Log log) {
        boolean isOk = false;
        try (FileWriter logWriter = new FileWriter(file, true)) {
            logWriter.write(createStringLog(log));
            isOk = true;
        } catch (IOException e) {
            Log.error(nameLog, "An error occurred IO", e.getStackTrace());
        }
        return isOk;
    }

    public void loadFromFile() {
        if (file.exists()) {
            try (FileReader logReader = new FileReader(file)) {
                int c;
                while ((c = logReader.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                Log.error(nameLog, "An error occurred IO", e.getStackTrace());
            }
        } else {
            Log.debug(nameLog, "File " + nameFile + " not found");
        }
    }


    private String createStringLog(Log log) {
        String result;
        if (log.getStacktrace() != null) {
            result = log.getDate().toString() + " "
                    + log.getLevel() + ": "
                    + log.getName() + ": "
                    + log.getMessage()
                    + " STACKTRACE "
                    + Arrays.toString(log.getStacktrace())
                    + '\n';
        } else {
            result = log.getDate().toString() + " "
                    + log.getLevel() + ": "
                    + log.getName() + ": "
                    + log.getMessage()
                    + '\n';
        }
        return result;
    }

}
