package utils.log;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public class LogToFile {

    String nameLog = "Log OnlineSchool";
    public final static String STR_PATH_SERVICE = "./src/utils/log/";
    public final static String STR_PATH_LOG = "./src/utils/log/";
    public final static String STR_NAME_LOG = "log.txt";
    public final static String STR_NAME_SERVICE = "Service.log";
    private static LogToFile instance;
    private final Path pathServiceFile;
    private final Path pathLogFile;
    private LogLevel defaultLogLevel = LogLevel.DEBUG;

    private LogToFile() {
        pathLogFile = Path.of(STR_PATH_LOG,STR_NAME_LOG);
        pathServiceFile = Path.of(STR_PATH_SERVICE,STR_NAME_SERVICE);
        createServiceFile(pathServiceFile);
        createLogFile(pathLogFile);
    }

    public static LogToFile getInstance() {
        if (instance == null) {
            instance = new LogToFile();
        }
        return instance;
    }

    private void createServiceFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.DEBUG);
            } catch (IOException e) {
                System.out.println("Error creation Service file");
            }
        }
    }

    private void createLogFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error creation Log file");
            }
        }
    }

    public void saveToLogFile(Log log) {
        if (Files.exists(pathLogFile)) {
            try {
                Files.write(pathLogFile, createStringLog(log).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Log file error");
            }
        }
    }


    public void saveToServiceFile(LogProperty logProperty, LogLevel logLevel) {
        Log.setLogLevel(logLevel);
        if (Files.exists(pathServiceFile)) {
            try {
                Files.write(pathServiceFile, (logProperty.name() + " = " + logLevel.name() + "    \n")
                        .getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                System.out.println("Service file error");
            }
        }
    }


    public void loadFromLogFile() {
        if (Files.exists(pathLogFile)) {
            try (BufferedReader reader = Files.newBufferedReader(pathLogFile)) {
                String value = reader.readLine();
                while (value != null) {
                    System.out.println(value);
                    value = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error reading log file");
            }
        }
    }

    public LogLevel loadFromServiceFile(LogProperty logProperty)  {
        LogLevel level = defaultLogLevel;
            try (BufferedReader reader = Files.newBufferedReader(pathServiceFile)) {
                String value = reader.readLine();
                while (value != null) {
                    if (value.contains(logProperty.name()) && value.contains("=") ) {
                        level = LogLevel.valueOf(value.substring(value.indexOf("=") + 1).trim());
                        break;
                    }
                    value = reader.readLine();
                }

        } catch (Exception e) {
                System.out.println( "File service " + '\"' + STR_NAME_SERVICE  + '\"'
                    + " reading  error. Default LOG_LEVEL = " + defaultLogLevel );

        }
        return level;
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

    public void setDefaultLogLevel(LogLevel defaultLogLevel) {
        this.defaultLogLevel = defaultLogLevel;
    }
}
