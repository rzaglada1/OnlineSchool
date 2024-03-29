package com.online_school.utils.log;

import com.online_school.Main;
import com.online_school.utils.MenuUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;


public class LogToFile {

    private final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    private final Locale locale = Locale.US;

    private static LogToFile instance;
    private final Path pathServiceFile;
    private final Path pathLogFile;
    private LogLevel defaultLogLevel = LogLevel.DEBUG;

    private LogToFile() {

        pathLogFile = Path.of(Main.STR_PATH_DIRECTORY, Main.STR_NAME_LOG);
        pathServiceFile = Path.of(Main.STR_PATH_DIRECTORY, Main.STR_NAME_SERVICE);
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
                saveToServiceFile(LogProperty.LOG_LEVEL, LogLevel.DEBUG, path);
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


    public void saveToServiceFile(LogProperty logProperty, LogLevel logLevel, Path path) {
        Log.setLogLevel(logLevel);
        if (Files.exists(path)) {
            try {
                Files.write(path, (logProperty.name() + " = " + logLevel.name() + "    \n")
                        .getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                System.out.println("Service file error");
            }
        }
    }

    public void loadFromLogFileFilter(LogLevel logLevel) {
        if (Files.exists(pathLogFile)) {
            try (Stream<String> fromFile = Files.lines(pathLogFile)) {
                fromFile.filter(element -> element.contains(logLevel.name())).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Error reading log file");
            }
        }
    }

    public void loadFromLogFileFilterByMiddle(LogLevel logLevel) {
        if (Files.exists(pathLogFile)) {
            try (Stream<String> fromFileNumberLine = Files.lines(pathLogFile); Stream<String> fromFile = Files.lines(pathLogFile)) {
                long countLine = fromFileNumberLine.count();
                if (countLine % 2 != 0) {
                    countLine++;
                }
                countLine = countLine / 2;
                System.out.println(countLine);

                fromFile.skip(countLine)
                        .filter(element -> element.contains(logLevel.name()))
                        .forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Error reading log file");
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

    public LogLevel loadFromServiceFile(LogProperty logProperty, Path path) {
        LogLevel level = defaultLogLevel;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String value = reader.readLine();
            while (value != null) {
                if (value.contains(logProperty.name()) && value.contains("=")) {
                    level = LogLevel.valueOf(value.substring(value.indexOf("=") + 1).trim());
                    break;
                }
                value = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("File service " + '\"' + Main.STR_NAME_SERVICE + '\"'
                    + " reading  error. Default LOG_LEVEL = " + defaultLogLevel);

        }
        return level;
    }


    private String createStringLog(Log log) {
        String result;
        if (log.getStacktrace() != null) {
            result = formatDate(log.getDate(), DATE_FORMAT, locale) + " "
                    + log.getLevel() + ": "
                    + log.getName() + ": "
                    + log.getMessage()
                    + " STACKTRACE "
                    + Arrays.toString(log.getStacktrace())
                    + '\n';
        } else {
            result = formatDate(log.getDate(), DATE_FORMAT, locale) + " "
                    + log.getLevel() + ": "
                    + log.getName() + ": "
                    + log.getMessage()
                    + '\n';
        }
        return result;
    }

    private String formatDate(LocalDateTime dateTime, String strFormat, Locale locale) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(strFormat, locale);
        return dateTime.format(df);
    }


    public void setDefaultLogLevel(LogLevel defaultLogLevel) {
        this.defaultLogLevel = defaultLogLevel;
    }
}
